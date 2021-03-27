package thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程 生命周期 状态 测试
 */
public class ThreadLifeTest {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();  //条件
        new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println(" synchronized1 wait");
                    //object.wait();
                    object.wait(5000);
                    System.out.println(" synchronized1 after waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "synchronizedThread1").start();

        new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println("synchronized2 notify");
                    //打开或关闭这段，观察synchronized1的状态
                    object.notify();
                    //notify之后当前线程并不会施法锁，只是被notify的线程从等待队列进入同步队列
                    //sleep也不会释放锁
                    Thread.sleep(5000);
                    System.out.println("synchronized2 destroy");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "synchronizedThread2").start();

        new Thread(() -> {
            lock.lock();
            System.out.println(" ReentrantLock1 waiting");
            try {
                //condition.await();
                condition.await(5, TimeUnit.SECONDS);
                System.out.println(" ReentrantLock1 after waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "ReentrantLock_Condition1").start();

        new Thread(()->{
            try {
                lock.lock();
                System.out.println("ReentrantLock2 signal");
                //打开或关闭这段，观察ReentrantLock1的状态
                condition.signal();
                // signal之后当前线程并不会释放锁，只是被signal的线程从等待队列进入同步队列
                // sleep也不会释放锁
                Thread.sleep(5000);
                System.out.println("ReentrantLock2 destroy");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"ReentrantLock_Condition2").start();

        Thread.sleep(10000);
        //获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }

        /**
         * 第一次结果 把object.notify()和 condition.signal()注释掉
         *      ReentrantLock_Condition2和synchronizedThread2 都运行完线程了
         *         [14]ReentrantLock_Condition1
         *         [12]synchronizedThread1 都处在等待状态，等待 notify()和signal()
         * 第二次结果 把object.notify() 和 condition.signal()取消注释
         *      object.notify() 和 condition.signal() 把 ReentrantLock_Condition1和synchronizedThread1 从等待状态唤醒但是 唤醒 俩个线程后 ReentrantLock_Condition1和synchronizedThread1进入了阻塞状态
         *      ReentrantLock2 和 synchronized2 运行完后 才不会阻塞
         * 第三次结果: 跟第二次结果相似
         */
    }
}
