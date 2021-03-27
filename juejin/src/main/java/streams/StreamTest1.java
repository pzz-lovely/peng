package streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Person {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class StreamTest1 {
    public static void main(String[] args) {
        //demo1();
        //demo2();
        demo3();

    }

    private static void demo3() {
    }

    /**
     *  计算列表中的元素数
     */
    private static void demo2() {
        List<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        System.out.println(list.size());
    }

    //控制台输出0～9的例子：
    private static void demo1() {
        IntStream.range(0,10).forEach(value-> System.out.println(value));
    }


}
