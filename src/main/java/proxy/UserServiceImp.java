package proxy;

/**
 * @Auther lovely
 * @Create 2020-02-04 14:02
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class UserServiceImp implements UserService {
    @Override
    public void addUser() {
        System.out.println("添加方法");
    }

    @Override
    public void updateUser() {
        System.out.println("更新");
    }

    @Override
    public void delUser() {
        System.out.println("删除");
    }
}
