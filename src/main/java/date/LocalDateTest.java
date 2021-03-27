package date;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Auther lovely
 * @Create 2020-01-12 12:35
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 本地时间
 *  LocalDate实例是不可变的，因此所有的计算都LocalDate返回 new LocalDate();
 *    创建对象
 *       LocalDate localDate =LocalDate.now();获取LocalDate与今天的本地日期相对应的日期
 *       LocalDate localDate = LocalDate.of(2015,12,31);创建一个LocalDate代表某一年的某月的某一天实例，但没有时区信息
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2020, 1, 12);
        LocalDateTime localDate3 = LocalDateTime.now();
        System.out.println(localDate3);
    }
}
