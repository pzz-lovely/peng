package threadcoreknowledge.uncaughtexception;

/**
 * @Auther lovely
 * @Create 2020-03-11 10:02
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
        new Thread(new UseOwnUncaughtExceptionHandler(),"myThread1").start();
        new Thread(new UseOwnUncaughtExceptionHandler(),"myThread1").start();
        new Thread(new UseOwnUncaughtExceptionHandler(),"myThread1").start();
        new Thread(new UseOwnUncaughtExceptionHandler(),"myThread1").start();
    }


    @Override
    public void run() {
        throw new RuntimeException();
    }
}
