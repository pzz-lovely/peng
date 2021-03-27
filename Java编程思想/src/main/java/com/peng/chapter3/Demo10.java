package com.peng.chapter3;

/**
 * @Auther lovely
 * @Create 2020-06-30 19:51
 * @Description 编写一个具有两个常量值的程序，一个具有交替的二进制位1和0，其中最低有效位为0，另一个也具有交替的二进制位1和0，但是其最低有效位为1
 * 。取这两个值，用位操作符以所有的方式结合运算它们，然后用Integer.toBinaryString()显示
 *
 * 电脑  存二进制
 *
 * & 只要有两个输入位都是1，则输出1
 * | 只要有其中一个输入位位1，输出1
 * ^ 取反
 */
public class Demo10 {
    public static void main(String[] args) {
        int i = 1 + 4 + 16 + 64;
        int j = 2 + 8 + 32 + 128;
        System.out.println("i = " + Integer.toBinaryString(i));
        System.out.println("j = " + Integer.toBinaryString(j));
        System.out.println("i & j " + Integer.toBinaryString(i & j));
        System.out.println("i | j " + Integer.toBinaryString(i | j));
        System.out.println("i ^ j " + Integer.toBinaryString(i ^ j));
        System.out.println(" ~i = " + Integer.toBinaryString(~i));
        System.out.println("~j " + Integer.toBinaryString(~j));


    }
}
