package threadcoreknowledge.createthreads;

/**
 * @Auther lovely
 * @Create 2020-03-06 16:30
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class RunnableStyle implements Runnable {


    public static void main(String[] args) {
        new Thread(new RunnableStyle(), "0.0");
    }
    @Override
    public void run() {
        System.out.println("用Runnable实现线程");
    }
}
