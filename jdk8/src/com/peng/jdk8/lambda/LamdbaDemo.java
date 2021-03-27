package com.peng.jdk8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @author lovely
 * @create 2021-03-10 16:29
 * @description
 */
public class LamdbaDemo {
    public static void main(String[] args) {
        demo(); // 1, 2, 3
        /*
        9527-1
        9527-2
        9527-3
         */
        structureDemo();
        // 2
        // 5
        // 7
        // 9
        returnValueDemo();
        // 3
        interfaceDemo();
        // 9527将是你的终身代号
        // 9527
        // false
        // 9527
        // 9527
        // 2
        functionDemo();
    }


    public static void demo() {
        Arrays.asList(1, 2, 3).forEach(System.out::println);
    }

    public static void structureDemo() {
        Arrays.asList(1, 2, 3).forEach(e -> {
            String name = "9527-" + e;
            System.out.println(name);
        });
    }

    public static void returnValueDemo() {
        List<Integer> list = Arrays.asList(9, 5, 2, 7);
                list.sort((e1, e2) -> e1.compareTo(e2));
        list.forEach(System.out::println);
    }

    public static void interfaceDemo(){
        FunctionalInterfaceDemo interfaceDemo = Integer::sum;
        System.out.println(interfaceDemo.computed(1, 2));
    }


    public static void functionDemo(){
        // 第一个为输入值，第二个输出值
        Function<Integer, String> function1 = (x) -> x + "将是你的终身代号";
        System.out.println(function1.apply(9527));

        // 第一个输入值，输出值 就是 输入值
        UnaryOperator<Integer> unaryOperator = x -> x + 527;
        System.out.println(unaryOperator.apply(9000));


        // 第一个为输入值，返回 boolean
        Predicate<Integer> predicate = (x) -> x == 9527;
        System.out.println(predicate.test(9528));

        // 第一个为输入值，没有返回值
        Consumer<String> consumer = System.out::println;
        consumer.accept("9527");

        // 没有输入值，只有输入值
        Supplier<String> supplier = () -> "9527";
        System.out.println(supplier.get());

        // 两个输入值，返回两个输入值类型的值
        BinaryOperator<Integer> binary = (x, y) -> x > y ? x : y;
        System.out.println(binary.apply(1, 2));
    }
}