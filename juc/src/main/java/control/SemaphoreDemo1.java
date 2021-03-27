package control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther lovely
 * @Create 2020-03-25 19:57
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示Semaphore用法
 */
public class SemaphoreDemo1 {
    static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            service.execute(new Task());
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try{
                semaphore.acquire(3);
                System.out.println(Thread.currentThread().getName()+" 拿到了许可证");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                System.out.println(Thread.currentThread().getName() + "释放了许可证");
                semaphore.release(3);
            }

        }
    }


}
