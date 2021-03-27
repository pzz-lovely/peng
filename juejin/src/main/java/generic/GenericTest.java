package generic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类型擦除
 */
public class GenericTest {
    public static <T extends Comparable<T>> List<T> sort(List<T> list){
        return Arrays.asList(list.toArray((T[]) new Comparable[list.size()]));
    }

    public static <T extends Comparable<T>> T[] sort2(List<T> list) {
        return list.toArray((T[])new Comparable[list.size()]);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        //这里调用正常
        System.out.println(sort(list).getClass());
        //这里调用报错
        System.out.println(sort2(list).getClass());
    }
}
