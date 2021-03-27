package mldn.cascade.util;

/**
 * @Auther lovely
 * @Create 2020-03-22 9:44
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class StringUtil {
    public static String initCap(String value) {
        if (value == null || "".equals(value)) {
            return value;
        }
        if (value.length() == 1) {
            return value.toUpperCase();
        }
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}

