package one.chapter6.proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther lovely
 * @Create 2020-02-19 20:32
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class AopHandler implements InvocationHandler {

    private AopLogger aopLogger ;
    private Person person = new Man();
    public AopHandler(AopLogger aopLogger) {
        this.aopLogger = aopLogger;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        aopLogger.before();
        System.out.println(method.getName());
        Object object = method.invoke(person, args);
        aopLogger.after();
        return null;
    }


    public Person getPerson() {
        return person;
    }
}
