package threadlocal;

/**
 * @Auther lovely
 * @Create 2020-03-20 8:34
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 演示ThreadLocal用法2：避免传递参数的麻烦
 */
public class ThreadLocalLocalNormalUsage06
{
    public static void main(String[] args) {
        new Service1().process();
    }
}
class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
class Service1{
    public void process(){
        User user = new User("0.0");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}
class Service2{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("线程2 "+user.name);
        new Service3().process();
    }
}
class Service3{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("线程3 "+user.name);
    }
}


