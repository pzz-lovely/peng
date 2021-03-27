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
        System.out.printf("��ǰ����%s-%s-%s", localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
        System.out.println("��ȡһ��ʱ����" + localDate.getDayOfWeek().getValue());
        System.out.println("��������һ�µ�����:" + localDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
        System.out.println("��������һ�������:" + localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        System.out.println("��������һ��ĵڼ���"+localDate.getDayOfYear());
    }
}
