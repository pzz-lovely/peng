package threadcoreknowledge.sixstates;

/**
 * @Auther lovely
 * @Create 2020-03-08 9:29
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 展示线程的 new Runnable ,Terminated状态。即使是正在运行，也是Runnable状态，而不是Running
 */
public class NewRunnableTerminated implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());
        //New
        System.out.println(thread.getState());
        thread.start();
        //Runnable
        System.out.println(thread.getState());
        Thread.sleep(10);
        //Runnable
        System.out.println(thread.getState());
        Thread.sleep(4000);
        //Terminated
        System.out.println(thread.getState());

    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
