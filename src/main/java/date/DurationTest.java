package date;

import java.time.Duration;
import java.time.Instant;

/**
 * @Auther lovely
 * @Create 2020-01-12 12:12
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description ����ʱ��
 *  ��ʾ����ʱ�䣬��������ʱ��֮���ʱ�䣬����Instant��һ��,Duration���������Ϊ��λ��ʾ��ʱ��
 *
 *  Duration���� ��ʾ����֮���һ��ʱ��Instant�Ķ��󡣽�Duration��ӵ�java����ʱ��
 *  һ��Durationʵ���ǲ��ɱ����ôһ�������������㲻�ܸı�����ֵ��
 *  ����Duration����
 *  ���ʳ���ʱ��
 *      һ��Duration�ڲ�������ֵ���
 *          ����ʱ������벿��
 *          ����ʱ��ĵڶ�����
 *        ���벿�ִ����Duration��С����Ĳ��֡��ڶ����ִ���Duration����һ��Ĳ���
 *          ʹ��
 *              getNano()   ��ȡ����
 *              getSeconds() ��ȡ��
 *       �����Խ�����ʱ����ת��ΪDurationΪ������λ:
 *          toNanos() ת��Ϊ����
 *          toMillis() תΪ����
 *          toMinutes() תΪ����
 *          toHours() Сʱ
 *          toDays() ��
 *      ����ʱ�����
 *          ��Duration�������һ�׷����������ִ�л��ڶԼ���Duration����
 *             ���:
 *               plusNanos()
 *               plusMillis();
 *               plusMinutes()
 *               plusHours()
 *               plusDays()
 *             ɾ��:
 *               minusMillis()
 *               minusSeconds()
 *               minusMinutes()
 *               minusHours()
 *               minusDays()
 */
public class DurationTest {
    public static void main(String[] args) throws InterruptedException {
        Instant first = Instant.now();
        System.out.println("first seconds "+first.getEpochSecond());
        //wait some time while something happens
        Thread.sleep(3000);
        Instant second = Instant.now();
        System.out.println("second seconds "+first.getEpochSecond());
        Duration duration = Duration.between(first, second);
        System.out.println("duration seconds "+duration.getSeconds()+" nano "+duration.getNano());

        System.out.println(duration.toMillis());

        //�������ʱ��
        //��ӵĳ���ʱ��
        Duration addDuration = duration.plusDays(3);
        Duration delDuration = duration.minusMillis(1000);
        System.out.println("addDuration day = "+addDuration.toDays()+" delDuration millis = "+delDuration.toMillis());
    }
}
