package mldn.math;

/**
 * @Auther lovely
 * @Create 2020-03-23 13:16
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MathDemo1 {
    public static void main(String[] args) {
        System.out.println("绝对值："+Math.abs(-10.3));
        System.out.println("绝对值："+Math.abs(10.3));
        System.out.println("最大值：" + Math.max(10, 20));
        System.out.println("最小值：" + Math.min(28, 10));
        System.out.println("正弦值" + Math.sin(90));
        System.out.println("对数值"+Math.log(30));
        System.out.println("四舍五入：" + Math.round(12.1));
        System.out.println("四舍五入：" + Math.round(12.51));
        System.out.println("四舍五入：" + Math.round(-12.1));
        System.out.println("四舍五入：" + Math.round(-12.51));
    }
}
