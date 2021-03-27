package one.chapter6.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther lovely
 * @Create 2020-02-19 19:21
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getCanonicalName());
        System.out.print("target : " + target);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if(i< args.length - 1) System.out.print(",");
            }
        }
        System.out.print(")");
        System.out.println();

        return method.invoke(target, args);



    }
}
