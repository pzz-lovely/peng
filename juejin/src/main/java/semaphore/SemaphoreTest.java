package semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest {
    public static final Semaphore SEMAPHORE = new Semaphore(100);
    public static final AtomicInteger failCount = new AtomicInteger(0);
    public static final AtomicInteger successCount = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> seckill()).start();
        }

    }

    private static boolean seckill(){
        if (!SEMAPHORE.tryAcquire()) {
            System.out.println("no permits,count=" + failCount.incrementAndGet());
            return false;
        }
        try{
            Thread.sleep(2000);
            System.out.println("seckill success,count="+successCount.incrementAndGet());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            SEMAPHORE.release();
        }
        return true;
    }
}
