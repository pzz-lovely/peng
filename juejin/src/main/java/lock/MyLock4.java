package lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class MyLock4 {
    private static class Node{
        //�洢��Ԫ��Ϊ�߳�
        Thread thread;
        //ǰһ���ڵ�
        Node prev;
        //��һ���ڵ�
        Node next;

        public Node(){}

        public Node(Thread thread, Node prev) {
            this.thread = thread;
            this.prev = prev;
        }

    }
    //����ģʽ
    private Node EMPTY = new Node();

    private static Unsafe unsafe = null;
    //��ʶ�Ƿ�����
    private volatile int state;
    private volatile Node head;
    private volatile Node tail;
    //ƫ����
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

    //ԭ�Ӹ���tail�ֶ�
    public boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    //ԭ�Ӹ���״̬
    public boolean compareAndSetState(int except, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, except, update);
    }


    public MyLock4(){
        //ͷ�ڵ� β�ڵ� ��Ϊ��  ͷ�ڵ������ ��β�ڵ� �����������
        head = tail = EMPTY;
    }

    public void lock(){
        //���Ը���state�ֶ�,���³ɹ�˵��ռ������
        if (compareAndSetState(0, 1)) {
            return;
        }
        //δ�������
        Node node = enqueue();
        Node prev = node.prev;
        //�ٴγ��Ի�ȡ������Ҫ�����һ���ڵ��ǲ���head,�����˳�����
        while (node.prev != head || !compareAndSetState(0, 1)) {
            //Ϊ��ȡ����������
            unsafe.park(false, 0L);
        }
        //���治��Ҫԭ�Ӹ��£���Ϊͬ��ֻ��һ���̷߳��ʵ�����
        //��ȡ������һ���ڵ���head��
        //head����һλ
        head = node;
        //��յ�ǰ�ڵ�����ݣ�Э��gc
        node.prev = null;
        node.next = null;
    }

    private Node enqueue(){
        while (true) {
            //��ȡβ�ڵ�
            Node t = tail;
            //�����½ڵ�
            Node newNode = new Node(Thread.currentThread(), t);
            //���ϳ���ԭ�Ӹ���β�ڵ�
            if (compareAndSetTail(t, newNode)) {
                //����β�ڵ�ɹ�����β�ڵ��nextָ��ָ��ǰ�ڵ�
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
