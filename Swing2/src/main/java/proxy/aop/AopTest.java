package proxy.aop;

import java.lang.reflect.Proxy;

/**
 * @Auther lovely
 * @Create 2020-03-16 21:41
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class AopTest {
    public static void main(String[] args) {
        Service service = (Service) Proxy.newProxyInstance(AopTest.class.getClassLoader(),
                new Class[]{Service.class},
                new AopHandler());
        System.out.println(service.say());

    }
}
