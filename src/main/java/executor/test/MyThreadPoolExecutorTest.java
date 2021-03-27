package executor.test;

import executor.DiscardRejectPolicy;
import executor.Executor;
import executor.MyThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ≤‚ ‘œﬂ≥Ã≥ÿ
 *
 */

public class MyThreadPoolExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        Executor threadPool = new MyThreadPoolExecutor("test", 5, 10, new ArrayBlockingQueue<>(15), new DiscardRejectPolicy());
        AtomicInteger num = new AtomicInteger(0);
        for (int i = 0; i < 10000; i++) {
            Thread.sleep(10);
            System.out.println("running : " + System.currentTimeMillis() + " : " + num.incrementAndGet());
        }
    }
}
