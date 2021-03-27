package runtimeAnnotations.ioc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @Auther lovely
 * @Create 2020-03-16 9:53
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */

public class Factory {
    private static String packageName;
    private static ConcurrentHashMap<String,Object> beans;
    private static LinkedHashSet<Class> hashSet ;
    static{
        try {
            hashSet = new LinkedHashSet();
            beans = new ConcurrentHashMap<>();
            packageName = "runtimeAnnotations.ioc";
            String className = "src/main/java/"+packageName.replace(".", "/");
            Files.list(Paths.get(className)).forEach(e -> {
                try {
                    String fileName = e.getFileName().toString();
                    hashSet.add(Class.forName(packageName+"."+fileName.substring(0,fileName.lastIndexOf("."))));
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            hashSet.forEach(clazz ->{
                //判断 使用有 Bean的注解
                if (clazz.isAnnotationPresent(Bean.class)) {
                    beans.put(clazz.getSimpleName(), instance(clazz));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String name) {
        Object obj = beans.get(name);
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Stream<Field> stream = Arrays.stream(fields);
        stream.forEach(field -> {
            //Autowired[] autowireds = field.getAnnotationsByType(Autowired.class);
            Resource[] resources = field.getAnnotationsByType(Resource.class);
            if (resources.length == 0) {
                return;
            }
            field.setAccessible(true);
            for (int i = 0; i < resources.length; i++) {
                Object o = beans.get(resources[i].value());
                try {
                    field.set(obj,o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return obj;
    }


    private static Object instance(Class clazz) {
        try{
            return clazz.getConstructor().newInstance();
        } catch ( NoSuchMethodException e) {
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


    public static ConcurrentHashMap<String, Object> getBeans() {
        return beans;
    }


}
