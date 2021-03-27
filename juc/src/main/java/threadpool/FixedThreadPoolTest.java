package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-19 15:27
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示newFixedThreadPool
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            service.execute(new Task());
        }

    }
}

class Task implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" = "+Thread.currentThread().getState());
    }
}
