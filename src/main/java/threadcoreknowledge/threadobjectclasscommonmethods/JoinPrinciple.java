package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * @Auther lovely
 * @Create 2020-03-09 11:29
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description join的代替写法
 */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Thread finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");

        };

        Thread thread1 = new Thread(r);
        thread1.start();
        System.out.println("开始等待子线程运行完毕");
        //等价代码
        synchronized (thread1) {
            thread1.wait();
        }
        System.out.println("所有线程执行完毕");
    }
}
