package mldn.com.peng;

/**
 * @Author lovely
 * @Create 2020-09-16 15:16
 * @Description todo
 */
public class Main {
    public static void main(String[] args) {
        UserService userService = ProxyUtil.newInstance(UserServiceImpl.class);
        System.out.println(userService.getName("a"));
    }
}