package mldn.com.peng;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author lovely
 * @Create 2020-09-16 15:03
 * @Description todo
 */
public class ProxyUtil {

    public static <T> T newInstance(Class<T> clazz) {
        try {
            // 创建对象
            T t = clazz.getConstructor().newInstance();
            SessionInvocationHandler sessionInvocationHandler = new SessionInvocationHandler(t);
            Class[] interfaces = clazz.getInterfaces();
            return (T) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(), interfaces,
                    sessionInvocationHandler);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    static class SessionInvocationHandler implements InvocationHandler {

        private Object object;

        public SessionInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            System.out.println("a");
            try {
                result = method.invoke(object, args);
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
            return result;
        }
    }
}