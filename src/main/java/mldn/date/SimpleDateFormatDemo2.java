package mldn.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Auther lovely
 * @Create 2020-03-20 22:16
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class SimpleDateFormatDemo2 {
    public static void main(String[] args) throws ParseException {
        String date = "2020-03-20 22:18:20 019";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(format.parse(date));

    }
}
