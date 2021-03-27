package threadcoreknowledge.stopthreads;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @Auther lovely
 * @Create 2020-03-06 21:59
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description run方法内没有sleep或wait方法时，停止线程
 *
 *要配合isInterrupted()方法
 */
public class RightWayStopThreadWithoutSleep implements Runnable {


    @Override
    public void run() {
        int num = 1;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num+"是一万的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
