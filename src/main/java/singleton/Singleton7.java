package singleton;

/**
 * @Auther lovely
 * @Create 2020-03-16 20:13
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 静态内部类 推荐用
 */
public class Singleton7 {

    private Singleton7(){}

    private static class SingletonInstance{
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
