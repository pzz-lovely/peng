package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Auther lovely
 * @Create 2020-03-09 10:47
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 每个一秒钟输出当前时间，被中断，观察
 * Thread.sleep()
 * TimeUnit.SECONDS.sleep()
 */
public class SleepInterrupted implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        /**/
        Thread.sleep(5000);
        thread.interrupt();
    }
    @Override
    public void run() {
            try{
                for (int i = 0; i < 10; i++) {
                    System.out.println(new Date());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                System.out.println("我被中断了");
                e.printStackTrace();
            }

    }
}
