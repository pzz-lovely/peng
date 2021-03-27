package aqs;

public class AbstractQueuedSynchronizedTest {
    //主要内部类
    static final class Node {
        //标识一个节点是共享模式
        static final Node SHARED = null;
        //标识一个节点是互斥模式
        static final Node EXCLUSIVE = null;

        //标识线程已取消
        static final int CANCELLED = 1;
        //标识后继节点需要唤醒
        static final int SIGNAL = -1;
        //标识线程等待在一个条件上
        static final int CONDITION = -2;
        //标识后面的共享锁需要无条件的传播(共享锁需要连续唤醒读的线程)
        static final int PROPAGATE = -3;

        //当前节点保存的线程对应的等待状态
        volatile int waitStatus;

        //前一个节点
        volatile Node prev;
        //后一个节点
        volatile Node next;
        //当前节点保存的线程
        volatile Thread thread;

        //下一个等待在条件上的节点(Condition锁时使用)
        Node nextWaiter;

        //是否是共享模式
        final boolean isShared(){
            return nextWaiter == SHARED;
        }
        //获取前一个节点
        final Node predecessor(){
            Node p = prev;
            if (p == null) {
                throw new NullPointerException();
            }else
                return p;
        }

        //节点构造方法
        Node(Thread thread, Node node) {
            //把共享模式还是互斥模式存储到nextWaa=iter这个字段里面了
            this.nextWaiter = node;
            this.thread = thread;
        }

        //节点的构造方法
        Node(Thread thread, int waitState) {
            //把共享模式还是互斥模式存储到nextWaiter这个字段里面了
            this.waitStatus = waitState;
            this.thread = thread;
        }
        //队列的头节点
        private transient volatile Node head;
        //队列的尾节点
        private transient volatile Node tail;
        //控制加锁解锁的状态变量
        private volatile int state;
    }
}
