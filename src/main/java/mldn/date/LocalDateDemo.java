package mldn.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Auther lovely
 * @Create 2020-03-21 19:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDate " + localDate);
        System.out.println("LocalTime " + localTime);
        System.out.println("LocalDateTime " + localDateTime);
    }
}
