package generic;

/**
 * java数组是不支持向下转型的
 */
public class ArrayTest {
    public static void main(String[] args) {
        Object[] objs = new Object[]{1};
        //类型转换错误
        //Integer[] ins = (Integer[])objs;
        Object[] objs2 = new Integer[]{1};
        //不报错
        Integer[] ins2 = (Integer[])objs2;


    }
}
