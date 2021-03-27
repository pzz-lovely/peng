package singleton;

/**
 * @Auther lovely
 * @Create 2020-03-16 19:46
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 饿汉式 (静态常量) (可用)
 * 优点:
 *   在类装载就完成了 实例化
 */
public class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
