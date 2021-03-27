package mldn.concurrent.skipList;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 *跳表示一种与平衡二叉树性能类型的数据结构，其主要是在有序链表上使用。在JUC提供的集合中，有两个支持有跳表操作的集合类型ConcurrentSkipListMap、ConcurrentSkipListSet
 * 跳表实现原理借简介
 *  数组是一种常见的线性结构，如果在进行索引查询时其时间复杂度为O(1)，但是在进行数据内容查询时，就必须基于有序存储并结合二分法进行查找，这样操作的时间复杂度为o(log2n)。在多数情况下，数组由于其固定长度的限制，所有开发中会通过链表来解决，但是如果要想进一步提升链表的查询性能，就必须采用跳表结构来处理。而跳表结构的本子是需要提供一个有序的链表集合，并从中依据二分法的原理抽取出一些样本数据，而后对样本数据的范围进行查询
 */
public class ConcurrentSkipListMapTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int x = 0; x < 10; x++) {
                    map.put(Thread.currentThread().getName()+"-"+x, x);
                }
                System.out.println(latch.getCount());
                latch.countDown();
            }, "线程" + i).start();
        }
        latch.await();
        System.out.println(map.get("线程88-8"));
        System.out.println(map);
    }
}
