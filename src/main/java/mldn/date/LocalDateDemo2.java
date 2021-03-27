package mldn.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

/**
 * @Auther lovely
 * @Create 2020-03-21 19:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class LocalDateDemo2 {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.printf("当前日期%s-%s-%s", localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
        System.out.println("获取一周时间数" + localDate.getDayOfWeek().getValue());
        System.out.println("今年所处一月的周数:" + localDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
        System.out.println("今天所处一年的周数:" + localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        System.out.println("今天所处一年的第几天"+localDate.getDayOfYear());
    }
}
