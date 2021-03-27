package mldn.concurrent.skipList;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Concurrent是线程安全的哈希表实现类，在实现结果中他将哈希表分成许多的片段(Segment),每一个片段中除了保存有哈希数据外还提供一个可重用的"互斥锁"，以片段的形式实现多线程操作，即在同一个片段内多个线程访问是互斥的，而不同的片段的访问采用的是异步处理方式，这样使得ConcurrentHashMap在保证数据的前提下有可以实现数据的正确修改
 */
public class concurrentHashMapTest {
    //多线程访问ConcurrentHashMap
    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int x = 0; x < 10; x++) {
                    map.put(Thread.currentThread().getName(), x);
                }
            },"线程"+i).start();
        }
        System.out.println(map.size());
        System.out.println(map.toString());
    }
}
