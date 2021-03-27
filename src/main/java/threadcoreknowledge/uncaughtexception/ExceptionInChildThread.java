package threadcoreknowledge.uncaughtexception;

/**
 * @Auther lovely
 * @Create 2020-03-11 9:25
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 单线程 抛出 处理 有异常栈堆
 */
public class ExceptionInChildThread implements Runnable{
    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
