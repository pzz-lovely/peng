package mldn.date;

import java.util.Calendar;

/**
 * @Auther lovely
 * @Create 2020-03-20 21:21
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CalendarDemo2 {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(); //��ȡ����
        calendar.set(calendar.get(Calendar.YEAR), 8, 1);//�����8����9��
        calendar.set(Calendar.DAY_OF_MONTH, -1);
        System.out.printf("��ǰ���ڸ�ʽ %s-%s-%s %s:%s:%s ",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH)+1,
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));

    }
}
