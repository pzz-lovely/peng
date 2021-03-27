package one.chapter6.proxy.aop;

import java.lang.reflect.Proxy;

/**
 * @Auther lovely
 * @Create 2020-02-19 20:39
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class AopTest {


    public static void main(String[] args) {
        AopLogger aopLogger = new AopLogger();
        AopHandler handler = new AopHandler(aopLogger);
        Person person = (Person) Proxy.newProxyInstance(AopTest.class.getClassLoader(), new Class[]{Person.class},
                handler);
        person.sayHi();
        System.out.println(person == handler.getPerson());
    }


}
