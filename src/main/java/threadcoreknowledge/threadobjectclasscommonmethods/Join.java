package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * @Auther lovely
 * @Create 2020-03-09 11:04
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示join 注意语句输出顺序，会变化
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        };
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        System.out.println("开始等待子线程运行完毕");
        thread1.join();
        thread2.join();
        System.out.println("所有线程执行完毕");
    }
}
