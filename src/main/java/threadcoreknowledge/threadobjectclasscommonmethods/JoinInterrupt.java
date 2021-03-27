package threadcoreknowledge.threadobjectclasscommonmethods;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Auther lovely
 * @Create 2020-03-09 11:08
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示join期间被中断的效果
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Runnable runnable = ()->{
            try{
                mainThread.interrupt();
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Thread finished.");
            } catch (InterruptedException e) {
                System.out.println("子线程中断");
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "主线程被中断");
            //e.printStackTrace();
            thread1.interrupt();
        }
        System.out.println("子线程运行完毕");
    }
}
