package date;

import java.time.Instant;

/**
 * @Auther lovely
 * @Create 2020-01-12 11:57
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 *    Instant ��ʾʱ�����ϵ�ʱ��˲�䣬��java7����ʱ��API�У�ʱ��ͨ������1��1�յĺ�������ʾ��1970����java8��Instant�����ʾʱ��㣬��ʱ����ʾ�� 1970��1��1�����������ʮ��֮һ��
 *
 *    Instant��java����ʱ�������ʱ������һ���ض���ʱ�̣�˲ʱ����Ϊ��ԭ���ƫ����(��Ϊepoch)
 *      ����Instant���� Instant.now();
 *      ����˲���ʱ��
 *          һ��Instant�����ڲ����������ֶΣ����а����Ա�ʾ��ʱ��Instant:
 *              ��ʱ������������
 *              ����
 *                getEpochSecond() ������
 *                getNano() ��������
 *       ��ʱ����
 *          instant��Ҳ�м��ַ�������������������ڼ���Instant
 */
public class InstantTest {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println("�� : " + instant.getEpochSecond());
        System.out.println("���� : " + instant.getNano());
    }
}
