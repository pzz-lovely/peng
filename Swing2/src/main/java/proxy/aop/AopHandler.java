package proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther lovely
 * @Create 2020-03-16 21:24
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class AopHandler implements InvocationHandler {

    private AopLogger logger = new AopLogger();
    private ServiceImpl service = new ServiceImpl();
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.before();
        Object obj = method.invoke(service,args);
        System.out.println(" 0.0 "+obj.getClass().getName());
        logger.after();
        return null;
    }
}
