package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-19 16:55
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 关闭线程池
 */
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            service.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        service.shutdownNow();
   /*     service.shutdown();
        service.execute(new ShutDownTask());*/

    }
}

class ShutDownTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断了");
        }
    }
}
