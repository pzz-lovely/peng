package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-19 15:34
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示固定 线程 产生 OOM
 * -Xmx8m -Xms8m
 */
public class FixedThreadPoolOOM {
    private static ExecutorService service = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.submit(new SubThread());
        }
    }
}

class SubThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
