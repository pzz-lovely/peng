package chapter5.lock;

import java.util.concurrent.CountDownLatch;

public class MutexTest {
    static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        Mutex mutex = new Mutex();
        CountDownLatch latch = new CountDownLatch(500);
        for (int i = 0; i < 300; i++) {
            new Thread(()->{
                try{
                    //mutex.lock();
                    count++;
                }finally{
                    //mutex.unlock();
                    latch.countDown();
                }
            }).start();
        }

        for (int i = 0; i < 200; i++) {
            new Thread(()->{
                try{
                    //mutex.lock();
                    count--;
                }finally {
                    //mutex.unlock();
                    latch.countDown();
                }
            }).start();
        }

        latch.await();
        System.out.println(count);
    }
}
