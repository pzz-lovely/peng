package collections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Auther lovely
 * @Create 2020-03-25 14:42
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 对比两个迭代器
 */
public class CopyOnWriteArrayListDemo2 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});
        System.out.println(list);
        Iterator<Integer> itr1 = list.iterator();
        list.add(4);
        System.out.println(list);
        Iterator<Integer> itr2 = list.iterator();

        itr1.forEachRemaining(System.out::println);
        System.out.println();
        itr2.forEachRemaining(System.out::println);
    }
}
