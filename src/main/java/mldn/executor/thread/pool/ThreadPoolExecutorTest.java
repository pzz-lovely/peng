package mldn.executor.thread.pool;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        //通过ThreadPoolExecutor创建线程池，该线程池有两个内核线程，每个线程存活时间为6秒
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 6L, TimeUnit.SECONDS, queue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 5; i++) {
            executor.submit(()->{
                System.out.println("Before " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("After " + Thread.currentThread().getName());
            });
        }
    }
}
