package mldn.classLoader.server;

import mldn.classLoader.FileClassLoader;

import java.lang.reflect.Method;

/**
 * @Auther lovely
 * @Create 2020-04-09 13:50
 * @Description todo
 */
public class ServerDemo {
    public static void main(String[] args) throws Exception {
        NetClassLoader netClassLoader = new NetClassLoader();
        Class<?> data = netClassLoader.getData("mldn.classLoader.Message");
        Object messageObject = data.getConstructor().newInstance();
        Method method = data.getMethod("echo", String.class);
        System.out.println(method.invoke(messageObject, "0.0"));
        System.out.println(messageObject);
    }
}
