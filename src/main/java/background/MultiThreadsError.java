package background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther lovely
 * @Create 2020-03-12 14:05
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示计数不准确 找出具体出错的位置
 */
public class MultiThreadsError implements Runnable {
    private static int index = 0;
    final boolean[] marked = new boolean[20001];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    static MultiThreadsError r = new MultiThreadsError();
    static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("index = "+index);
        System.out.println("真正运行到的次数 :" + realIndex);
        System.out.println("错误的运行次数:"+wrongCount);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            cyclicBarrier2.reset();
            try {
                cyclicBarrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;        //两个线程读取出现问题
            cyclicBarrier1.reset();
            try {
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (r) {
                if (marked[index] /*&& marked[index-1]*/) {
                    System.out.println("发生了错误" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
