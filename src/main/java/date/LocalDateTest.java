package date;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Auther lovely
 * @Create 2020-01-12 12:35
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description ����ʱ��
 *  LocalDateʵ���ǲ��ɱ�ģ�������еļ��㶼LocalDate���� new LocalDate();
 *    ��������
 *       LocalDate localDate =LocalDate.now();��ȡLocalDate�����ı����������Ӧ������
 *       LocalDate localDate = LocalDate.of(2015,12,31);����һ��LocalDate����ĳһ���ĳ�µ�ĳһ��ʵ������û��ʱ����Ϣ
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2020, 1, 12);
        LocalDateTime localDate3 = LocalDateTime.now();
        System.out.println(localDate3);
    }
}
