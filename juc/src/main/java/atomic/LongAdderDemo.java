package atomic;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Auther lovely
 * @Create 2020-03-22 18:30
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示高并发场景下，LongAdder比AtomicLong性能好
 */
public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder counter = new LongAdder();
        ExecutorService service = Executors.newFixedThreadPool(20);
        Instant start = Instant.now();
        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(counter));
        }
        service.shutdown();
        while(!service.isTerminated());
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        System.out.println("LongAdder耗时:秒数"+between.getSeconds());
        System.out.println("LongAdder耗时:毫秒数数"+between.toMillis());
        System.out.println(counter.sum());
    }


    private static class Task implements Runnable{
        private LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }
}
