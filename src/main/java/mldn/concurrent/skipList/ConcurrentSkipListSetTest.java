package mldn.concurrent.skipList;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Set集合实现无重复数据的跳表操作
 */
public class ConcurrentSkipListSetTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        Set<String> set = new java.util.concurrent.ConcurrentSkipListSet<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int x = 0; x < 10; x++) {
                    set.add(Thread.currentThread().getName() +"-"+ x);
                }
                latch.countDown();
            }, "线程" + i).start();
        }
        latch.await();
        System.out.println(set.contains("线程20-2"));
        System.out.println(set.toString());

    }
}
