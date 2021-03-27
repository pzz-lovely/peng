package com.peng;

/**
 * @Auther lovely
 * @Create 2020-05-25 21:34
 * @Description resolver
 */
public class ThreadState {
    private static Object waitObj = new Object();   //作为唯一的锁资源
    private static Object blockedObj = new Object();


    public static void main(String[] args) throws InterruptedException {
        Thread wait = new Thread(new Waiting(), "WaitingThread");
        Thread timeWait = new Thread(new TimeWaiting(), "TimeWaitingThread");
        Thread blocked1 = new Thread(new Blocked(), "BlockedThread1");
        Thread blocked2 = new Thread(new Blocked(), "BlockedThread2");


        wait.start();
        timeWait.start();
        blocked1.start();
        blocked2.start();
        // 延迟100毫秒
        Thread.sleep(100);

        System.out.println("Waiting="+wait.getState());
        System.out.println("TimeWaiting="+timeWait.getState());
        System.out.println("Blocked1="+blocked1.getState());
        System.out.println("Blocked2="+blocked2.getState());
    }

    // 线程等待
    static class Waiting implements Runnable{
        @Override
        public void run() {
            synchronized (waitObj) {
                try {
                    waitObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 线程计时等待
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 线程阻塞
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (blockedObj) {
                while (true) {

                }
            }
        }
    }
}
