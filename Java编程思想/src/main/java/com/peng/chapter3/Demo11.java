package com.peng.chapter3;

/**
 * @Auther lovely
 * @Create 2020-06-30 20:01
 * @Description
 * 数字的二进制表示形式成为"有符号的二进制补码"
 * << 左移操作符能按照操作符的右侧指定的位数将操作符左边的操作数向左移移动在低位补0
 * >> 有符号右移操作符则按照操作符右侧指定的位数将操作符左边的操作数向右移动。有符号操作符使用"符号扩展"：若符号为正，则在高位插入0，若符号为高位插入0
 * 无符号>>> 操作符，无论正负都插入0
 *
 *
 * 以一个最高有效位为1二进制数字开始，用有符号右移操作符对其进行右移，直至所欲的二进制位都被移出为止，每移一位都要使用Integer.toBinaryString()展示结果
 */
public class Demo11 {
    public static void main(String[] args) {
        int h = 9;
        System.out.println(h<<1);
        System.out.println(Integer.toBinaryString(h));
        for (int i = 0; i < 28; i++) {
            // h >>=1 ;
            h >>>= 1;
            // h <<= 1;
            System.out.println(h+"，二进制位："+Integer.toBinaryString(h));
        }
    }
}
