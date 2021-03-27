package computer;

import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-04-13 11:47
 * @Description todo
 */
public class Test {
    public static void main(String[] args) {
        DoubleLinedList<String> list = new DoubleLinedList(10);
        list.addHeader("000");
        list.addHeader("1111");
        list.addTail("222");
        list.addTail("333");
        list.remove("1111");
        System.out.println(Arrays.toString(list.toArray()));
    }
}
