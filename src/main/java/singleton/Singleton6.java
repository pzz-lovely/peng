package singleton;

/**
 * @Auther lovely
 * @Create 2020-03-16 19:52
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 推荐面试使用 双重检查
 *  线程安全 延迟加载 效率较高
 *  为什么要double -check
 *      1.线程安全
 *      2.check行不行
 *      3.性能问题
 *   为什么要用volatile：
 *      1。新建对象其实有3个步骤
 *          新建一个空的Person对象
 *          把这个对象的地址指向p
 *          执行Person的构造函数
 *  可能会对 构造里面方法 进行重排序，可能会 先实例化对象 然后在对变量赋值
 *      2.重排序 会带来 NPE
 *      3.防止重排序
 */
public class Singleton6 {
    private volatile static Singleton6 INSTANCE;
    private Singleton6(){}

    public static Singleton6 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton6.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton6();
                }
            }
        }
        return INSTANCE;
    }
}
