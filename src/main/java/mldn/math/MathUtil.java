package mldn.math;

/**
 * @Auther lovely
 * @Create 2020-03-23 13:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MathUtil {
    public static double round(double num, int scale) {
        return Math.round(num * Math.pow(10.0, scale) / Math.pow(10.0, scale));
    }
}
