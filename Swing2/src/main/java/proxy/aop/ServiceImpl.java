package proxy.aop;

/**
 * @Auther lovely
 * @Create 2020-03-16 21:42
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ServiceImpl implements Service {

    @Override
    public String say() {
        System.out.println("我在说话");
        return "0.0";
    }
}
