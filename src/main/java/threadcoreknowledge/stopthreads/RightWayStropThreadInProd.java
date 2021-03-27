package threadcoreknowledge.stopthreads;

/**
 * @Auther lovely
 * @Create 2020-03-07 8:45
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 最佳实战
 * catch了InterriptedException之后的优先选择: 在方法声明抛出异常
 * 那么run()就会强制try/catch
 */
public class RightWayStropThreadInProd  implements Runnable{

    @Override
    public synchronized void run() {
        System.out.println(Thread.currentThread().getName()+"正在运行....");
        while(true && !Thread.currentThread().isInterrupted()) {
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程执行完毕");
    }

    private int index = 0;

    private void throwInMethod() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"go"+index++);
        Thread.sleep(1000);
    }


    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new RightWayStropThreadInProd();
        Thread thread2 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        thread1.start();
        thread2.start();
        Thread.sleep(2000);
        thread1.interrupt();
        Thread.sleep(2000);
        thread2.interrupt();
    }
}
