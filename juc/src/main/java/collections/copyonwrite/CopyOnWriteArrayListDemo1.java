package collections.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Auther lovely
 * @Create 2020-03-25 14:20
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示CopyOnWriteArrayList可以在迭代的过程中修改数组内容，但是ArrayList不行。
 */
public class CopyOnWriteArrayListDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        /*java.util.concurrent.CopyOnWriteArrayList<String> list = new java.util.concurrent.CopyOnWriteArrayList();*/

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("list is " + list);
            String next = iterator.next();
            System.out.println(next);
            if (next.equals("2")) {
                list.remove("5");
            }
            if (next.equals("3")) {
                list.add("3 found");
            }
        }
    }
}
