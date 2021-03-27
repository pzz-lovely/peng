package reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * ��ȡ���� ���췽��
 *   getDeclaredConstructor();
 * ��ȡ���췽������Ĳ���
 *   getParameterTypes()
 */
public class GetConstructors {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cls = Person.class;
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        for (int i = 0; i < declaredConstructors.length; i++) {
            System.out.print(Modifier.toString(declaredConstructors[i].getModifiers()));
            Class[] para = declaredConstructors[i].getParameterTypes();
            for (Class p : para) {
                System.out.print(p.getName());
            }
            System.out.println();
        }

        Class[] paraClass  = new Class[]{String.class};
        Constructor constructor = cls.getDeclaredConstructor(paraClass);
        constructor.setAccessible(true);
        Person person = (Person) constructor.newInstance("0.0");
        System.out.println(person.toString());;

    }
}
