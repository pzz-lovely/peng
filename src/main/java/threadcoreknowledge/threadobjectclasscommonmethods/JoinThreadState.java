package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Auther lovely
 * @Create 2020-03-09 11:15
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 先join在mainThread.getState()
 * 通过debug看线程Join前后状态的对比
 */
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
        thread.join();
        System.out.println("子线程运行完毕");
    }
}
