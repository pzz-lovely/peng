package singleton;

/**
 * @Auther lovely
 * @Create 2020-03-16 19:52
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description (懒汉式) 线程不安全 [不可用]
 *
 */
public class Singleton3 {
    private static Singleton3 INSTANCE;
    private Singleton3(){}

    public static Singleton3 getInstance() {
        if (INSTANCE == null) {
            return new Singleton3();
        }
        return INSTANCE;
    }
}
