package runtimeAnnotations.annotationFactory;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;

/**
 * @Auther lovely
 * @Create 2020-03-16 9:04
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Message message = new Message();
        System.out.println(message.echo("测试"));
    }
}


class Factory {
    private Factory(){}

    public static <T> T getInstance(String className) {
        try{
            return (T)Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Action {
    String value();
}

@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
@interface Install {
    String value();
}

@Action("runtimeAnnotations.annotationFactory.RadioChannel")
class Message { ///消息的发送信息
    private IChannel channel;
    @Install("runtimeAnnotations.annotationFactory.Factory")
    public Message(){
        try {
            Action actionAnnotation = super.getClass().getAnnotation(Action.class);

            Install installAnnotation = super.getClass().getConstructor().getAnnotation(Install.class);
            System.out.println(installAnnotation);
            String factoryClassName = installAnnotation.value();
            Class<?> factoryClass = Class.forName(factoryClassName);
            System.out.println(factoryClass.getMethod("getInstance", String.class));
            this.channel = (IChannel) factoryClass.getMethod("getInstance", String.class).invoke(null,
                    actionAnnotation.value());
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public String echo(String msg) throws Exception {
        String echoMessage = "0.0 消息发送失败";
        if (channel.build()) {
            echoMessage = "0.0 : " + msg;
            channel.close();
        }
        return echoMessage;
    }
}

interface IChannel extends AutoCloseable{
    boolean build();    //建议通道
}
class InternetChannel implements IChannel{
    @Override
    public boolean build() {
        System.out.println("InternetChannel 建建立通道");
        return true;
    }
    @Override
    public void close() throws Exception {
        System.out.println("关闭InternetChannel 通道");
    }
}

class RadioChannel implements IChannel{
    @Override
    public boolean build() {
        System.out.println("RadioChannel 建立通道");
        return true;
    }
    @Override
    public void close() throws Exception {
        System.out.println("关闭 RadioChannel 通道");
    }
}


