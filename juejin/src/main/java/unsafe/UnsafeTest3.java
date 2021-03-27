package unsafe;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

public class UnsafeTest3 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        User user = new User();
        Field age = user.getClass().getDeclaredField("age");
        unsafe.putInt(user, unsafe.objectFieldOffset(age), 20);
        System.out.println(user.getAge());

    }

    //使用正常方式抛出异常
    public static void reaFile() throws IOException {
        throw new IOException();
    }

    //使用Unsafe抛出异常不需要再方法签名上往外抛
    public static void readFileUnsafe(Unsafe unsafe){
        unsafe.throwException(new IOException());
    }
}
