package one.chapter6.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @Auther lovely
 * @Create 2020-02-19 19:32
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] objects = new Object[100];
        for (int i = 0; i < objects.length; i++) {
            Integer value = i+1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            objects[i] = proxy;
        }
        Integer key = new Random().nextInt(objects.length - 1);

        System.out.println("key" + objects[key]);

        int result = Arrays.binarySearch(objects, key);

        if(result >= 0) System.out.println(objects[result]);
    }
}
