package mldn.concurrent.arrays;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 与CopyOnWriteArrayList类似的集合还有CopyOnWriteArraySet它提供一种无序的线程安全集合机构，可以理解为线程安全的HashSet实现：但是与HashSet区别在于:HashSet是基于散列方式存放的，而CopyOnWriteArraySet是与数组
 */
public class CopyOnWriteArraySetTest {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int x = 0; x < 10; x++) {
                    set.add(" 元素 " + x);
                }
            } ).start();
        }
        System.out.println(set.size());
        System.out.println(set.toString());
    }
}
