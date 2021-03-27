package threadcoreknowledge.sixstates;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther lovely
 * @Create 2020-03-08 9:37
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 展示 Blocked Waiting TimedWaiting
 */
public class BlockedWaitingTimedWaiting implements Runnable {
    Lock lock = new ReentrantLock();



    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting r = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        Thread.sleep(500);
        //Timed_Waiting
        System.out.println("线程1"+thread1.getState());
        //Blocked
        System.out.println("线程2"+thread2.getState());

        Thread.sleep(800);
        //wait
        System.out.println("线程1"+thread1.getState());
        r.notifyAll();
    }

    @Override
    public void run() {
        try {
            blockedMethod();
            /*blockedMethod2();*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void blockedMethod() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread()+"抢到锁了");
        wait();
    }

    public void blockedMethod2() throws InterruptedException {
        try{
            lock.lock();
            Thread.sleep(2000);
            /*线程1WAITING
              线程2TIMED_WAITING*/
            System.out.println(Thread.currentThread()+"抢到锁了");
        }finally {
            lock.unlock();
        }
    }
}
