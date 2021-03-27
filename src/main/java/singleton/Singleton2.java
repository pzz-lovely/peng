package singleton;

/**
 * @Auther lovely
 * @Create 2020-03-16 19:50
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 饿汉式 (静态代码块) (可用) 和一个效果 类似
 */
public class Singleton2 {
    private final static Singleton2 INSTANCE;

    static{
        INSTANCE = new Singleton2();
    }

    private Singleton2(){}

    private static Singleton2 getInstance(){
        return INSTANCE;
    }
}
