package lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class MyLock4 {
    private static class Node{
        //存储的元素为线程
        Thread thread;
        //前一个节点
        Node prev;
        //后一个节点
        Node next;

        public Node(){}

        public Node(Thread thread, Node prev) {
            this.thread = thread;
            this.prev = prev;
        }

    }
    //互斥模式
    private Node EMPTY = new Node();

    private static Unsafe unsafe = null;
    //标识是否被锁定
    private volatile int state;
    private volatile Node head;
    private volatile Node tail;
    //偏移量
    private static long tailOffset;
    private static long stateOffset;


    static {
        Class c = Unsafe.class;
        try {
            Field field = c.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            tailOffset = unsafe.objectFieldOffset(MyLock4.class.getDeclaredField("tail"));

            stateOffset = unsafe.objectFieldOffset(MyLock4.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //原子更新tail字段
    public boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    //原子更新状态
    public boolean compareAndSetState(int except, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, except, update);
    }


    public MyLock4(){
        //头节点 尾节点 都为空  头节点的引用 是尾节点 问题就在这里
        head = tail = EMPTY;
    }

    public void lock(){
        //尝试更新state字段,更新成功说明占有了锁
        if (compareAndSetState(0, 1)) {
            return;
        }
        //未更新入队
        Node node = enqueue();
        Node prev = node.prev;
        //再次尝试获取锁，需要检测上一个节点是不是head,按入队顺序加锁
        while (node.prev != head || !compareAndSetState(0, 1)) {
            //为获取到锁，阻塞
            unsafe.park(false, 0L);
        }
        //下面不需要原子更新，因为同事只有一个线程访问到这里
        //获取锁且上一个节点是head、
        //head后移一位
        head = node;
        //清空当前节点的内容，协助gc
        node.prev = null;
        node.next = null;
    }

    private Node enqueue(){
        while (true) {
            //获取尾节点
            Node t = tail;
            //构造新节点
            Node newNode = new Node(Thread.currentThread(), t);
            //不断尝试原子更新尾节点
            if (compareAndSetTail(t, newNode)) {
                //更新尾节点成功，让尾节点的next指针指向当前节点
                t.next = newNode;
                return newNode;
            }
        }
    }

    public void unlock(){
        state = 0;
        Node next = head.next;
        if (next != null) {
            unsafe.unpark(next.thread);
        }
     }
    private static int count = 0 ;
    public static void main(String[] args) throws InterruptedException {
        MyLock4 lock = new MyLock4();
        CountDownLatch latch = new CountDownLatch(1000);
        IntStream.range(0,1000).forEach(i -> new Thread(()->{
            lock.lock();
            try{
                IntStream.range(0,10000).forEach(j -> {
                    count++;
                });
            }finally{
                lock.unlock();
            }
            latch.countDown();
        }).start());

        latch.await();
        System.out.println(count);
    }
}
