package lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class MyLock2 {
    private static class Node {
        //当前节点的线程
        Thread thread;
        //前一个节点
        Node prev;
        //后一个节点
        Node next;

        public Node() {
        }

        public Node(Thread thread, Node prev) {
            this.thread = thread;
            this.prev = prev;
        }
    }
        static final Node EMPTY = new Node();

        //标识是否被锁定
        private volatile int state;
        //链表头
        private volatile Node head;
        //链表尾
        private volatile Node tail;
        //stated的偏移量
        private static long stateOffset;
        //tail的偏移量
        private static long tailOffset;
        //Unsafe的实例
        private static Unsafe unsafe;
        static {
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                unsafe = (Unsafe) f.get(null);
                //获取state的偏移量
                stateOffset = unsafe.objectFieldOffset(MyLock2.class.getDeclaredField("state"));
                //获取tail的偏移量
                tailOffset = unsafe.objectFieldOffset(MyLock2.class.getDeclaredField("tail"));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    //原子更新state字段 比较和更新状态
    private boolean compareAndSetState(int expect,int update){
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    /**
     * 原子更新 tail链表尾
     * @param expect
     * @param update
     * @return
     */
    private boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    //构造方法
    public MyLock2(){
        head = tail = EMPTY;
    }

    //入队
    public Node enqueue(){
        while (true) {
            //获取尾节点
            Node t = tail;
            //构造新节点
            Node node = new Node(Thread.currentThread(), t);
            //不断尝试原子更新尾节点
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return node;
            }
        }
    }


    //加锁
    public void lock(){
        //尝试更新state字段，更新成功说明 占有了锁
        if (compareAndSetState(0,1)) {
            return;
        }
        //为更新成功入队
        Node node = enqueue() ;
        Node prev = node.prev;
        //再次尝试获取锁，需要检测上一个节点是不是Head,按入队顺序加锁
        while (node.prev != head || !compareAndSetState(0, 1)) {
            unsafe.park(false, 0L);
        }
        //下面不需要原子更新，因为同时只有一个线程访问到这里
        //获取到锁了且上一个节点是head
        //head后移一位
        head = node;
        //清空当前节点的内容，协助GC
        node.thread = null;
        //将上一个节点从链表中剔除，写入GC
        node.prev = null;
        prev .next = null;
    }

    public void unlock(){
        //把state更新成0，这里不要原子更新了，因为同事只有一个线程访问到这里
        state = 0;
        //下一个待唤醒的节点
        Node next = head.next;
        //下一个节点不为空，就唤醒它
        if (next != null) {
            unsafe.unpark(next.thread);
        }
    }
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        MyLock2 lock = new MyLock2();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        IntStream.range(0, 1000).forEach(i -> new Thread(() -> {
            lock.lock();
            try {
                IntStream.range(0, 10000).forEach(j -> {
                    count++;
                });
            } finally {
                lock.unlock();
            }
            countDownLatch.countDown();
        }, "tt-" + i).start());
        countDownLatch.await(); //主线程睡眠
        System.out.println(count);

    }
}
