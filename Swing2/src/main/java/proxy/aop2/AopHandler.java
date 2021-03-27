package proxy.aop2;

import proxy.aop.Service;
import proxy.aop.ServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther lovely
 * @Create 2020-03-17 7:46
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class AopHandler implements InvocationHandler {
    private Service service = new ServiceImpl();
    private AopLogger logger = new AopLogger();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.before();
        method.invoke(service, args);
        logger.after();
        return null;
    }
}
