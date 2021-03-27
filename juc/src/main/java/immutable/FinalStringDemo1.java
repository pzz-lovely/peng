package immutable;

/**
 * @Auther lovely
 * @Create 2020-03-23 11:10
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FinalStringDemo1 {
    public static void main(String[] args) {
        String a ="wukong2";
        final String b = "wokong";
        String d = "wokong";
        String c = d+2;
        String e = d+2;
        //a b d c是指向常量池的
        //e 只能在运行时才能判断 他在堆上
        System.out.println(a == c);
        System.out.println(a == e);
        System.out.println("嗷嗷");
        //true false
    }
}
