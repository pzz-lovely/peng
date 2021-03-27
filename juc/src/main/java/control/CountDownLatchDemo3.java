package control;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-25 17:27
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 比赛的场景 begin 多等1 end 1等多
 */
public class CountDownLatchDemo3 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i+1;
            service.execute(()->{
                try {
                    System.out.println("准备完毕，等待发令枪");
                    begin.await();
                    System.out.println(no+"开跑");
                    Thread.sleep((long) (Math.random()*10000));
                    System.out.println(no+"跑到终点了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    end.countDown();
                }
            });
        }
        Thread.sleep(2000);
        System.out.println("发令枪响");
        begin.countDown();
        end.await();
        System.out.println("全部跑完了");
    }
}
