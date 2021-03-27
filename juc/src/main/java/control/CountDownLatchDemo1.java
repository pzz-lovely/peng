package control;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-25 17:12
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 工厂中，质检，5个工人检查，所有都认为通过，才通过
 */
public class CountDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i+1;
            service.submit(()->{
                try {
                    Thread.sleep((long)(Math.random()*10000));
                    System.out.println("No."+no+"完成了检查。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("检查完成了");
        service.shutdownNow();
    }
}
