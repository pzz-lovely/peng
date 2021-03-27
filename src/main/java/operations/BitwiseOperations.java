package operations;

/**
 *按位 操作符
 *  ~非 取反操作  是一元操作符 不能与=一起  值为1则为0 为0则为1
 *  ^异或 值的某一位是1 但不全是1 则返回1
 *  &与 两个值都为1时 才返回1 否则返回0
 *  |或 只要有一个值为1 返回1 否则返回0
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
