package runtimeAnnotations.ioc;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-03-16 10:14
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FactoryTest {

    @Test
    public void getBeanTest(){
        System.out.println(Factory.getBeans());
        Message message = (Message) Factory.getBean("Message");
        System.out.println(message.echoChannel("测试"));;
    }

    @Test
    public void packageTest() throws IOException {
        String packageName = "src/main/java/runtimeAnnotations/ioc";
        Files.list(Paths.get(packageName)).forEach(e ->{
            String fileName = e.getFileName().toString();
            System.out.println(fileName.substring(0,fileName.lastIndexOf(".")));
        });
    }

    @Test
    public void classNameTest(){
        Class clazz = getClass();
        System.out.println("name : " + clazz.getName());
        System.out.println("simpleName : "+clazz.getSimpleName());
    }

    @Test
    public void beanTest() {
        System.out.println(Factory.getBeans());

    }


    private InternetChannel internetChannel = new InternetChannel();
    private IChannel channel = new InternetChannel();
    @Test
    public void instanceofTest() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class the = getClass();
        Field internetChannel = the.getDeclaredField("internetChannel");
        Field channel = the.getDeclaredField("channel");
        System.out.println(Arrays.toString(internetChannel.getType().getInterfaces()));
        System.out.println(internetChannel.getType().getSuperclass());
    }
}
