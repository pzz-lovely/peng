package juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try{
                    System.out.println("Aid thread is waiting fir starting");
                    startSignal.await();
                    System.out.println("Aid thread doing something");
                    doneSignal.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(200);
        System.out.println("Main thread is doing something");
        startSignal.countDown();
        // main thread do sth else
        System.out.println("Main thread is waiting for aid threads finishing.");
        doneSignal.await();

        System.out.println("Main thread is doing something after all threads have finished.");

    }
}
