package operations;

/**
 *��λ ������
 *  ~�� ȡ������  ��һԪ������ ������=һ��  ֵΪ1��Ϊ0 Ϊ0��Ϊ1
 *  ^��� ֵ��ĳһλ��1 ����ȫ��1 �򷵻�1
 *  &�� ����ֵ��Ϊ1ʱ �ŷ���1 ���򷵻�0
 *  |�� ֻҪ��һ��ֵΪ1 ����1 ���򷵻�0
 */
public class BitwiseOperations {
    public static void main(String[] args) {
       /* int i1 = 0xaaaaaaaa;
        int i2 = 0x55555555;*/
       int i1 = 1 ;
       int i2 = 0;

        System.out.println("i1 = " + Integer.toBinaryString(i1));
        System.out.println("i1 ~= "+Integer.toBinaryString(~i1));
        System.out.println("i1 & i1 = " + Integer.toBinaryString(i1 & i1));
        System.out.println("i1 & i2 = " + Integer.toBinaryString(i1 & i2));
        System.out.println("i1 | i1 = " + Integer.toBinaryString(i1 | i1));
        System.out.println("i1 | i2 = " + Integer.toBinaryString(i1 | i2));

        System.out.println("i1 ^ i1 = " + Integer.toBinaryString(i1 ^ i1));
        System.out.println("i1 ^ i2 = " + Integer.toBinaryString(i1 ^ i2));

        System.out.println("i2 = " + Integer.toBinaryString(i2));
        System.out.println("i2 = " + Integer.toBinaryString(~i2));
    }
}
