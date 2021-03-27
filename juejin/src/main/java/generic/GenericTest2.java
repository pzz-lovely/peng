package generic;

import java.util.Arrays;
import java.util.List;

/**
 * java里的泛型是假泛型，只在编译期有效，在运行时是没有泛型的概念
 */
public class GenericTest2 {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("1");
        List<Integer> intList = Arrays.asList(1);
        //打印true
        System.out.println(strList.getClass() == intList.getClass());
    }
}
