package one.chapter6.proxy.aop;

/**
 * @Auther lovely
 * @Create 2020-02-19 20:45
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Man implements Person {

    @Override
    public void sayHi() {
        System.out.println("ÄãºÃ");
    }

    @Override
    public double tax() {
        return 1000;
    }

    @Override
    public int sayHi(String worlds) {
        System.out.println(worlds);
        return worlds.length();
    }
}
