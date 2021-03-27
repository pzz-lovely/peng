package runtimeAnnotations.demo;

import java.lang.annotation.Annotation;

/**
 * @Auther lovely
 * @Create 2020-03-16 8:42
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("-------获取annotation注解");
        Class clazz = Message.class;
        for (Annotation annotation : clazz.getDeclaredAnnotations()) {
            System.out.println(annotation);
        }
    }
}
