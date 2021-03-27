package unsafe;

import sun.misc.Unsafe;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class InstantialTest  {
    private static Unsafe unsafe;
    static{
        try{
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, IOException, ClassNotFoundException, InvocationTargetException, CloneNotSupportedException {
        //构造方法
        User user = new User();
        //Class，里面实际也是反射
        User user1 = User.class.newInstance();
        //反射
        User user2 = User.class.getConstructor().newInstance();
        //克隆
        User user5 = (User) user.clone();
        //序列化
        User user3 = unserialize(user);
        //Unsafe
        User user4 = (User) unsafe.allocateInstance(User.class);
        System.out.println(user.age);
        System.out.println(user1.age);
        System.out.println(user2.age);
        System.out.println(user3.age);
        System.out.println(user4.age);
        System.out.println(user5.age);

    }

    private static User unserialize(User user) throws IOException, ClassNotFoundException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("D:\\Object.txt"));
        os.writeObject(user);
        os.close();

        ObjectInputStream is = new ObjectInputStream(new FileInputStream("D:\\Object.txt"));
        //反序列化
        User user1 = (User) is.readObject();
        is.close();
        return user1;
    }
    static class User implements Cloneable, Serializable {
        private int age;
        public User(){
            this.age = 10 ;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
