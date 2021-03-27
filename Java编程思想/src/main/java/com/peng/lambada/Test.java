package com.peng.lambada;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-07-03 22:24
 * @Description
 *
 * 函数式编程关心数据的映射，函数编程中的lambda可以看成两个类型之间的关系，一个输入类型和一个输出类型。你给它一个输入类型的值，则可以得到一个输出类型的值
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(12, 4, 5, 7, 29));
        list.stream().filter(e ->  e > 6).forEach(e -> {
            System.out.println(e);
        });
        list.stream().filter(e ->  e > 6).map( e -> e+10).forEach(e -> {
            System.out.println(e);
        });
    }
}
