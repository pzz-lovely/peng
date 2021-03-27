package mldn.bigNumber;

import java.math.BigInteger;

/**
 * @Auther lovely
 * @Create 2020-03-24 18:34
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class BigIntegerDemo {
    public static void main(String[] args) {
        BigInteger bigA = new BigInteger("9923243234234");
        BigInteger bigB = new BigInteger("2349234325490");

        System.out.println("加法运算:" + bigA.add(bigB));
        System.out.println("减法运算:" + bigA.subtract(bigB));
        System.out.println("乘法运算:" + bigA.multiply(bigB));
        System.out.println("除法运算:" + bigA.divide(bigB));

    }
}
