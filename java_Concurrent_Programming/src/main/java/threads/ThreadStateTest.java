package threads;

import java.util.concurrent.TimeUnit;

public class ThreadStateTest {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();
        //使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();
    }

    //该线程不断地进行睡眠
    static class TimeWaiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName()+"开始睡觉");
                SleepUtils.second(10);
            }
        }
    }

    //该线程在Waiting.class实例上等待
    static class Waiting implements Runnable{

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"开始等待");
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //该线程在Blocked.class实例上加锁后，不会释放该锁
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while(true) {
                    System.out.println(Thread.currentThread().getName()+"开始阻塞");
                    SleepUtils.second(10);
                }
            }
        }
    }
}

class SleepUtils{
    public static void second(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
