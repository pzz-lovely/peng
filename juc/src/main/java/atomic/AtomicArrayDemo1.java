package atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Auther lovely
 * @Create 2020-03-22 17:24
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示原子数组的使用方法 自旋锁
 */
public class AtomicArrayDemo1 {
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
    private static CountDownLatch countDownLatch = new CountDownLatch(200);
    public static void main(String[] args) throws InterruptedException {
        Decrement decrement = new Decrement(atomicIntegerArray,countDownLatch);
        Increment increment = new Increment(atomicIntegerArray,countDownLatch);
        Thread[] thread1 = new Thread[100];
        Thread[] thread2 = new Thread[100];
        for (int i = 0; i < 100; i++) {
            thread1[i] = new Thread(decrement);
            thread2[i] = new Thread(increment);
            thread1[i].start();
            thread2[i].start();
        }
        countDownLatch.await();
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("发现了错误"+i+" "+atomicIntegerArray.get(i));
            }
        }
        System.out.println("运行结束");
    }
    
}

class Decrement implements Runnable {
    private AtomicIntegerArray array;
    private CountDownLatch countDownLatch;
    public Decrement(AtomicIntegerArray array,CountDownLatch countDownLatch) {
        this.array = array;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
        countDownLatch.countDown();
    }
}

class Increment implements Runnable {
    private AtomicIntegerArray array;

    private CountDownLatch countDownLatch;
    public Increment(AtomicIntegerArray array,CountDownLatch countDownLatch) {
        this.array = array;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
        countDownLatch.countDown();
    }
}
