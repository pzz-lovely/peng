package singleton;

/**
 * @Auther lovely
 * @Create 2020-03-16 19:52
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description (懒汉式) 线程安全 [不推荐]
 *
 */
public class Singleton4 {
    private static Singleton4 INSTANCE;
    private Singleton4(){}

    public synchronized static Singleton4 getInstance() {
        if (INSTANCE == null) {
            return new Singleton4();
        }
        return INSTANCE;
    }
}
