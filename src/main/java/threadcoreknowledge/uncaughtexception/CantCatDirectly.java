package threadcoreknowledge.uncaughtexception;

/**
 * @Auther lovely
 * @Create 2020-03-11 9:33
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 1.不加 try catch 抛出4个异常，都带线程名字
 * 2.加了 try catch 期望捕获到一个线程的异常，线程 234 不应该运行，希望看到打印Caught Exception
 * 3.执行时发现，根本没有CaughtException，线程234依然运行并抛出异常
 * 说明线程的异常不能用传统方法捕获
 */
public class CantCatDirectly implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(new CantCatDirectly(), "MyThread1").start();
            new Thread(new CantCatDirectly(), "MyThread2").start();
            new Thread(new CantCatDirectly(), "MyThread3").start();
            new Thread(new CantCatDirectly(), "MyThread4").start();
        } catch (RuntimeException r) {
            System.out.println("Caught Exception");
        }
        Thread.sleep(300);
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
