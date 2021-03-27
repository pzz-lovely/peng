package lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class MyLock3 {
    private static class Node{
        Thread thread;
        //ǰһ���ڵ�
        Node prev;
        //��һ���ڵ�
        Node next;
        public Node(){}

        public Node(Thread thread, Node node) {
            this.thread = thread;
            this.prev = node;
        }
    }


    static final Node EMPTY = new Node();
    //��ʶ�Ƿ�����
    private volatile int state;
    private volatile Node head;
    private volatile Node tail;
    //state��ƫ����
    private static long stateOffset;
    private static long tailOffset;
    private static Unsafe unsafe;

    static {
        try{
            //��ȡUnsafe��ʵ��
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            //��ȡstate��ƫ����
            stateOffset = unsafe.objectFieldOffset(MyLock3.class.getDeclaredField("state"));
            //��ȡtail��ƫ����
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
        //ͷ�ڵ� β�ڵ� ��Ϊ��  ͷ�ڵ������ ��β�ڵ� �����������
        head = tail = EMPTY;
    }


    public void lock(){
        //�ж��Ƿ����߳� ���е�����
        if (compareAndSetState(0, 1)) {
            return;
        }
        Node node = enqueue();
        Node prev = node.prev;
        while (node.prev != this.head || !compareAndSetState(0, 1)) {
            unsafe.park(false, 0L);
        }
        //���治��Ҫԭ�Ӹ��£���Ϊֻ��һ���̵߳�����
        //��ȡ����������һ���ڵ���head
        //head����һλ
        head = node;
        //�� ��һ���ڵ���������޳� ��help gc
        node.prev = null;
        prev.next = null;
    }

    private Node enqueue(){
        while (true) {
            Node tail = this.tail;
            //�µĽڵ� ��һ���ڵ������Ϊ β�ڵ�
            Node newNode = new Node(Thread.currentThread(), tail);
            //���½ڵ�����Ϊ β�ڵ�
            if (comparedAndSetTail(tail,newNode)) {
                //����ǰ��β�ڵ� ����һ������Ϊ ��ǰ�½ڵ�
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


