package exchanges;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-08-20 17:06
 * @Description todo
 */
public class Test {
    private final int a = 10;
    private static final int b = 10;
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Test test = new Test();
        Field a = Test.class.getDeclaredField("b");
        a.setAccessible(true);
        //  Can not set static final int field exchanges.Test.a to java.lang.Integer
        a.set(Test.b, 1);
        System.out.println(a.get(test));


    }
}
