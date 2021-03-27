package runtimeAnnotations;

import java.awt.event.ActionListener;
import java.lang.reflect.*;

/**
 * @Auther lovely
 * @Create 2020-03-15 20:39
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ActionListenerInstaller {
    public static void processAnnotations(Object object) {
        try{
            Class<?> cl = object.getClass();
            for (Method m : cl.getDeclaredMethods()) {
                ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
                if (a != null) {
                    //获取 带名称的button
                    Field f = cl.getDeclaredField(a.source());
                    f.setAccessible(true);
                    addListener(f.get(object), object, m);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param source 调用 listener的数据源 比如button
     * @param param listener 实例
     * @param method listener 所要调用的方法
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void addListener(Object source, final Object param, final Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method mm, Object[] args) throws Throwable {
                return method.invoke(param);
            }
        };
        Object listener = Proxy.newProxyInstance(null, new Class[]{java.awt.event.ActionListener.class}, handler);
        //真正调用addListener
        Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
        adder.invoke(source, listener);
    }
}
