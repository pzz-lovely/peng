package lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class MyLock3 {
    private static class Node{
        Thread thread;
        //前一个节点
        Node prev;
        //后一个节点
        Node next;
        public Node(){}

        public Node(Thread thread, Node node) {
            this.thread = thread;
            this.prev = node;
        }
    }


    static final Node EMPTY = new Node();
    //标识是否被锁定
    private volatile int state;
    private volatile Node head;
    private volatile Node tail;
    //state的偏移量
    private static long stateOffset;
    private static long tailOffset;
    private static Unsafe unsafe;

    static {
        try{
            //获取Unsafe的实例
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            //获取state的偏移量
            stateOffset = unsafe.objectFieldOffset(MyLock3.class.getDeclaredField("state"));
            //获取tail的偏移量
            tailOffset = unsafe.objectFieldOffset(MyLock3.class.getDeclaredField("tail"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private boolean comparedAndSetTail(Node except, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, except, update);
    }


    public MyLock3(){
        //头节点 尾节点 都为空  头节点的引用 是尾节点 问题就在这里
        head = tail = EMPTY;
    }


    public void lock(){
        //判断是否有线程 持有到了锁
        if (compareAndSetState(0, 1)) {
            return;
        }
        Node node = enqueue();
        Node prev = node.prev;
        while (node.prev != this.head || !compareAndSetState(0, 1)) {
            unsafe.park(false, 0L);
        }
        //下面不需要原子更新，因为只有一个线程到这里
        //获取到锁了且上一个节点是head
        //head后移一位
        head = node;
        //将 上一个节点从链表中剔除 ，help gc
        node.prev = null;
        prev.next = null;
    }

    private Node enqueue(){
        while (true) {
            Node tail = this.tail;
            //新的节点 上一个节点的引用为 尾节点
            Node newNode = new Node(Thread.currentThread(), tail);
            //将新节点设置为 尾节点
            if (comparedAndSetTail(tail,newNode)) {
                //将以前的尾节点 的下一个引用为 当前新节点
                tail.next = newNode;
                return newNode;
            }
        }
    }


    public void unlock(){
        state = 0 ;
        Node t =  this.tail;
        Node next = head.next;
        if (next != null) {
            unsafe.unpark(next.thread);

        }
    }


    private static int count =  0 ;

    public static void main(String[] args) throws InterruptedException {
        MyLock3 lock = new MyLock3();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        IntStream.range(0, 1000).forEach(i -> new Thread(() -> {
            lock.lock();
            try {
                IntStream.range(0, 1000).forEach(j -> {
                    count++;
                });
            } finally {
                lock.unlock();
            }
            countDownLatch.countDown();
        }, "tt-" + i).start());
        countDownLatch.await();
        System.out.println(count);
    }
}


