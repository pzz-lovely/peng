package singleton;

/**
 * @Auther lovely
 * @Create 2020-03-16 19:52
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description (懒汉式) 线程不安全 [不推荐]
 *
 */
public class Singleton5 {
    private static Singleton5 INSTANCE;
    private Singleton5(){}

    public static Singleton5 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton5.class) {
                INSTANCE = new Singleton5();
            }
        }
        return INSTANCE;
    }
}
