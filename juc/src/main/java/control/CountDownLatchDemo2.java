package control;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-25 17:27
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 模拟一百米跑步，5名选手都准备好了，只等裁判员一生令下 多等一
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i+1;
            service.execute(()->{
                try {
                    System.out.println("准备完毕，等待发令枪");
                    count.await();
                    System.out.println(no+"开跑");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(2000);
        count.countDown();

    }
}
