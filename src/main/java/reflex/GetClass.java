package reflex;

/**
 * 获取Class类的有三种方式
 *  Class.forName(String name);
 *  .class
 *  getClass() object的方法
 */
public class GetClass {
    public static void main(String[] args) throws ClassNotFoundException {
        java.lang.Class<?> str1 = java.lang.Class.forName("java.lang.String");
        java.lang.Class str2 = GetClass.class;
        GetClass cls = new GetClass();
        java.lang.Class str3 = cls.getClass();
    }
}
