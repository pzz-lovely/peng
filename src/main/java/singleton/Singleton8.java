package singleton;

/**
 * @Auther lovely
 * @Create 2020-03-16 20:13
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 枚举 推荐用
 * 还可以防反序列化重新创建新的对象。
 */
public enum Singleton8 {
    INSTANCE;
    public void say(){
        int a = 0;
        System.out.println(a++);
    }

}
