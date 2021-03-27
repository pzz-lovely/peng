package one.chapter9.treeSet;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @Auther lovely
 * @Create 2020-02-20 10:29
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class TreeSetTest {
    public static void main(String[] args) {
        /*这是人为 定义的 根据 partNumber比较*/
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Aoaster", 3333));
        parts.add(new Item("Cidget", 1234));
        parts.add(new Item("Bodem", 4562));

        System.out.println(parts);

        /*getDescription作为比较的键 */
        NavigableSet<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));

        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);

    }
}
