package reflex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @PackgeName: reflex
 * @ClassName: GetField
 * @Author: 20573
 * Date: 2019/12/27 16:31
 * project name: peng
 * @Version:
 * @Description: ªÒ»°Field
 */
public class GetField {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class cls = Person.class;
        Person person = (Person) cls.getConstructor(String.class,String.class,int.class).newInstance("0.0","0.1",30);
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        System.out.println(field.get(person));;
        field.set(person, 0.1);

    }
}
