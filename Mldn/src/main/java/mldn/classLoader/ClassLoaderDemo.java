package mldn.classLoader;

import java.lang.reflect.Method;

/**
 * @Auther lovely
 * @Create 2020-04-09 13:02
 * @Description todo
 */
public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        FileClassLoader classLoader = new FileClassLoader();
        Class<?> data = classLoader.getData("mldn.classLoader.Message");
        Object messageObject = data.getConstructor().newInstance();
        Method method = data.getMethod("echo", String.class);
        System.out.println(method.invoke(messageObject, "0.0"));
        System.out.println(messageObject);
    }
}
