package thread;

import java.util.Arrays;
import java.util.List;

/**
 * 并行计算,可以提高程序运行的效率，多线程并行执行
 */
public class CreateThread04 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //串行
        list.stream().forEach(System.out::print);
        System.out.println();
        //并行
        list.parallelStream().forEach(System.out::print);
    }
}
