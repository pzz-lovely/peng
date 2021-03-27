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
        //������-��Ŀ�����������
        /**
         * newProxyInstance(ClassLoader loader,CLass<?>[] interfaces,InvocationHandler h)
         * ����1: ���������һ��д��ǰ��
         * ����2: ����������Ҫ�Ľӿ�
         * ����3: �����࣬һ��д������
         */
        UserService proxyService = (UserService) Proxy.newProxyInstance(MyBeanFactory.class.getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //ִ����
                        aspect.before();
                        Object obj = method.invoke(userService, args);
                        aspect.after();
                        return null;
                    }
                });
        return proxyService;
    }
}
