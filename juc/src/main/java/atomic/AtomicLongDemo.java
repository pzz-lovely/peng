package atomic;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther lovely
 * @Create 2020-03-22 18:30
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示高并发场景下，LongAdder比AtomicLong性能好
 */
public class AtomicLongDemo {
    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong();
        ExecutorService service = Executors.newFixedThreadPool(20);
        Instant start = Instant.now();
        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(counter));
        }
        service.shutdown();
        while(!service.isTerminated());
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        System.out.println(between.getSeconds());
        System.out.println(between.toMillis());
        System.out.println(counter.get());
    }


    private static class Task implements Runnable{
        private AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }
}
