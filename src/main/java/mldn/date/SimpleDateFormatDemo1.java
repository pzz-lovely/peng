package mldn.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-03-20 22:16
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class SimpleDateFormatDemo1 {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(format.format(date));

    }
}
