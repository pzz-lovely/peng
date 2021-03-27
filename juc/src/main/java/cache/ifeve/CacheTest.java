package cache.ifeve;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-26 19:32
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CacheTest {

    private static final ThreadLocal<Random> local = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
            return new Random();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(20);
        LocalCache<Integer> cache = new LocalCache<>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            final int no = i;
            service.execute(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cache.put(no + "", no*12, 6000+no*1000);
                latch.countDown();
            });
        }
        latch.await();
        cache.start();
        for (int i = 0; i < 20; i++) {
            System.out.print(cache.get(i+"")+" ");
        }
        System.out.println();
        Thread.sleep(7500);
        for (int i = 0; i < 20; i++) {
            System.out.print(cache.get(i+"")+" ");
        }
        System.out.println();
        Thread.sleep(7500);
        for (int i = 0; i < 20; i++) {
            System.out.print(cache.get(i+"")+" ");
        }
        System.out.println();
        service.shutdownNow();
        cache.stop();
    }
}
