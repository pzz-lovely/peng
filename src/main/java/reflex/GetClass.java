package reflex;

/**
 * ��ȡClass��������ַ�ʽ
 *  Class.forName(String name);
 *  .class
 *  getClass() object�ķ���
 */
public class GetClass {
    public static void main(String[] args) throws ClassNotFoundException {
        java.lang.Class<?> str1 = java.lang.Class.forName("java.lang.String");
        java.lang.Class str2 = GetClass.class;
        GetClass cls = new GetClass();
        java.lang.Class str3 = cls.getClass();
    }
}
