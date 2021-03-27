package cache;

import java.util.concurrent.CountDownLatch;

public class LocalCacheTest {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        LocalCache localCache = new LocalCache();
        int n = 500;
        int m = 100000;
        CountDownLatch latch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            new Thread(()->{
                for (int j = 0; j < m; j++) {
                    localCache.put(j + " ", new Object(), 10);
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("size " + localCache.getCache().size());
        System.out.println("cur " + localCache.getCur().get());
        System.out.println("ºÄÊ± "+(System.currentTimeMillis() - start));

    }
}
