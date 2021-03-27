package mldn.date;

import java.time.LocalDate;

/**
 * @Auther lovely
 * @Create 2020-03-21 19:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class LocalDateDemo3 {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.parse("1982-01-23");
        System.out.println("闰年判断 ：" + localDate.isLeapYear());
        System.out.println("所处的一周时间数："+localDate.getDayOfWeek());
    }
}
