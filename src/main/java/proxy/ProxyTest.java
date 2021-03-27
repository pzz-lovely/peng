package proxy;

/**
 * @Auther lovely
 * @Create 2020-02-04 14:18
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ProxyTest {
    public static void main(String[] args) {
        UserService userService = MyBeanFactory.createUserService();
        userService.addUser();

    }
}
