package runtimeAnnotations.myAnnotationFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @Auther lovely
 * @Create 2020-03-16 9:32
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Factory {
    private Factory(){}

    public static <T> T getInstance(String className) {
        try{
            Class<?> clazz = Class.forName(className);
            return (T)clazz.getDeclaredConstructor().newInstance();
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
