package generic;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * java里的泛型是假泛型，只在编译器有效，在运行时是没有泛型的概念的
 *
 * java中的泛型只在编译器期有效，在运行时只有调用者知道它自己需要什么类型，而调用者调用泛型方法自己做强制转换，被调用者是完全无干感的，被调用者只能尽力拿到它所知道的类型
 */
public class GenericTest3 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> strList = new ArrayList<>();
        Method addMethod = strList.getClass().getMethod("add", Object.class);
        addMethod.invoke(strList, 1);
        addMethod.invoke(strList, true);
        addMethod.invoke(strList, new Long(1));
        addMethod.invoke(strList, new Byte[]{1});
        //打印 [1, true, 1, 1]
        System.out.println(strList);

    }
}
