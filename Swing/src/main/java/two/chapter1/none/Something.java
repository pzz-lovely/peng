package two.chapter1.none;

/**
 * @Auther lovely
 * @Create 2020-02-28 16:07
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Something {
    public Something(){}

    public Something(String something){
        System.out.println(something);
    }

    public static String startWith(String s) {
        return String.valueOf(s.charAt(0));
    }
    public String endWith(String s) {
        return String.valueOf(s.charAt(s.length() - 1));
    }


}
