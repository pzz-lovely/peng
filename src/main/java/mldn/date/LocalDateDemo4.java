package mldn.date;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @Auther lovely
 * @Create 2020-03-21 19:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class LocalDateDemo4 {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.parse("1982-01-23");
        System.out.println("所在月的第一天" + localDate.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("所在月的第二天 " + localDate.withDayOfMonth(2));
        System.out.println("所在月的最后一天" + localDate.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("300年后的日期 " + localDate.plusYears(300));
        System.out.println("300月后的日期"+localDate.plusMonths(3));
    }
}
