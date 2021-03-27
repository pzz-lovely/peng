package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

class User{
    int age;
    public User(){
        age = 10;
    }

    public int getAge() {
        return age;
    }
}

public class UnsafeTest2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        User user = (User) unsafe.allocateInstance(User.class);
        System.out.println(user.age);
    }
}
