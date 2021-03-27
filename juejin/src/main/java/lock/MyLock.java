package lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class MyLock {
    private static class Node{
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

    static final Node EMPTY = new Node();
    //标识是否被锁定
    private volatile int state;
    //链表头
    private volatile Node head;
    //链表尾
    private volatile Node tail;

    //state的偏移量
    private static long stateOffset;
    //tail的偏移量
    private static long tailOffset;
    //Unsafe的实例
    private static Unsafe unsafe;

    static {
        try{
            //获取 Unsafe 的实例
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            //获取state的偏移量
            stateOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
            //获取tail的偏移量
            tailOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("tail"));
            System.out.println("stateOffset:" + stateOffset + ",tailOffset:" + tailOffset);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //原子更新state字段
    private boolean compareAndSetState(int expect,int update ){
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    //原子更新tail字段
    private boolean compareAndSetTail(Node expect,Node update){
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    public MyLock(){
        //头节点 和 尾节点 都为空
        head = tail = EMPTY;
    }

    /**
     * 加锁操作，首先判断当前线程状态是(0为持有当前锁，1持有了)
     *  没持有锁
     *     先调用 enqueue()方法
     */
    public void lock(){
        //尝试更新state字段，更新成功说明占有了锁
        if (compareAndSetState(0, 1)) {
            return;
        }
        //未更新成功入队
        Node node = enqueue();  //为创建的新节点
        Node prev = node.prev;
        //再次尝试获取锁，需要检测上一个节点是不是head,按入队顺序加锁
        while (node.prev != head || !compareAndSetState(0, 1)) {
            unsafe.park(false, 0L);
        }
        //下面不需要原子更新，因为同时一个线程访问到这里
        //获取到锁了且上一个节点是head
        //head后移一位
        head = node;
        //清空当前节点的内容，协助 GC
        node.thread = null;
        //将 上一个节点从链表中剔除，协助GC
        node.prev = null;
        prev.next = null;
    }

    /**
     * 自旋的方式
     * 先获取当前尾节点，构造新节点node = new Node(Thread.currentThread() ,tail) ，然后不断尝试更新尾节点
     *    如果更新成功了
     *       tail.prev  = tail;
     *       t.next = node;
     *       return node;
     * @return
     */
    private Node enqueue(){
        while (true) {
            //获取尾节点
            Node t = tail;
            //构造新节点
            Node node = new Node(Thread.currentThread(), t);
            //不断尝试原子更新 新节点为尾节点
            if (compareAndSetTail(t, node)) {
                //更新尾节点成功了，让原尾节点的next指针指向当前节点
                t.next = node;
                return node;
            }
        }
    }

    //解锁
    public void unlock(){
        //把state更新成0，这里不需要原子更新，因为同时只有一个线程访问到这里
        state = 0 ;
        //下一个待唤醒的节点
        Node next = head.next;
        //下一个节点不为空，就唤醒它
        if (next != null) {
            unsafe.unpark(next.thread);
        }
    }

    private static int count = 0 ;

    public static void main(String[] args) throws InterruptedException {
        MyLock lock = new MyLock();
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
