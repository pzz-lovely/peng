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
        System.out.println("��ӷ���");
    }

    @Override
    public void updateUser() {
        System.out.println("����");
    }

    @Override
    public void delUser() {
        System.out.println("ɾ��");
    }
}
