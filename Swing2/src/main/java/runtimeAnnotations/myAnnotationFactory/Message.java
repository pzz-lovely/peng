package runtimeAnnotations.myAnnotationFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther lovely
 * @Create 2020-03-16 9:35
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */

@Action("runtimeAnnotations.myAnnotationFactory.RadioChannel")
public class Message {
    private IChannel channel;


    @Install("runtimeAnnotations.myAnnotationFactory.Factory")
    public Message(){
        try {
            System.out.println(super.getClass());
            Class clazz = super.getClass();
            Install install = (Install) clazz.getDeclaredConstructor().getAnnotation(Install.class);
            Action action = (Action) clazz.getDeclaredAnnotation(Action.class);
            Class<?> factoryClass = Class.forName(install.value());
            Method instance = factoryClass.getMethod("getInstance", String.class);
            channel = (IChannel) instance.invoke(null, action.value());
            System.out.println(instance);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public String echo(String msg) {
        String echoMessage = "消息发送失败";
        if (channel.build()) {
            echoMessage = "发送的消息 : " + msg;
        }
        return echoMessage;
    }
}
