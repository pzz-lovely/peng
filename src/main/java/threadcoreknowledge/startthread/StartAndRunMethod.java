package threadcoreknowledge.startthread;

/**
 * @Auther lovely
 * @Create 2020-03-06 17:11
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description start和run两种启动线程的方式
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        //由main线程执行
        runnable.run();
        new Thread(runnable).start();
    }
}
