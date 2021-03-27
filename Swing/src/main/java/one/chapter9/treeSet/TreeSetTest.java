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
        /*������Ϊ ����� ���� partNumber�Ƚ�*/
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Aoaster", 3333));
        parts.add(new Item("Cidget", 1234));
        parts.add(new Item("Bodem", 4562));

        System.out.println(parts);

        /*getDescription��Ϊ�Ƚϵļ� */
        NavigableSet<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));

        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);

    }
}
