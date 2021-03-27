package atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther lovely
 * @Create 2020-03-22 17:15
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description AtomicInteger的基本用法，对比非原子类的线程安全问题，使用了原子类之后，不需要加锁，也可以保证线程安全
 */
public class AtomicIntegerDemo1 {
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public static void incrementAtomic(){
        //atomicInteger.getAndIncrement();
        //atomicInteger.getAndDecrement();
        atomicInteger.getAndAdd(1);
        atomicInteger.getAndAdd(-1);
    }

    private static volatile int basisCount = 0;

    private static CountDownLatch count = new CountDownLatch(2);

    public static void incrementBasic(){
        basisCount++;
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> increment()).start();
        new Thread(() -> increment()).start();
        count.await();
        System.out.println(atomicInteger.get());
        System.out.println(basisCount);
    }

    public static void increment(){
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
        count.countDown();
    }

}
