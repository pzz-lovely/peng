package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther lovely
 * @Create 2020-02-04 14:04
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MyBeanFactory {
    public static UserService createUserService(){
        final UserService userService = new UserServiceImp();
        final MyAspect aspect = new MyAspect();
        //代理类-将目标类和切面结合
        /**
         * newProxyInstance(ClassLoader loader,CLass<?>[] interfaces,InvocationHandler h)
         * 参数1: 类加载器，一般写当前类
         * 参数2: 代理类所需要的接口
         * 参数3: 处理类，一般写匿名类
         */
        UserService proxyService = (UserService) Proxy.newProxyInstance(MyBeanFactory.class.getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //执行者
                        aspect.before();
                        Object obj = method.invoke(userService, args);
                        aspect.after();
                        return null;
                    }
                });
        return proxyService;
    }
}
