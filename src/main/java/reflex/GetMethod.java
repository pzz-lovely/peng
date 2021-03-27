package reflex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @PackgeName: reflex
 * @ClassName: GetMethod
 * @Author: 20573
 * Date: 2019/12/27 15:58
 * project name: peng
 * @Version:
 * @Description: 获取方法
 */
public class GetMethod {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class cls = Person.class;
        Class[] paraClass = new Class[]{String.class};
        Person person = (Person) cls.getConstructor(String.class, String.class, int.class).newInstance("0.0", "0.1", 20);
        //调用私有方法
        Method method = cls.getDeclaredMethod("sayName", paraClass);
        method.setAccessible(true);
        method.invoke(person, "0.0");
    }
}
