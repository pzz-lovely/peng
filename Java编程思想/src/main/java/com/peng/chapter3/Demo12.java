package com.peng.chapter3;

/**
 * @Auther lovely
 * @Create 2020-06-30 20:10
 * @Description
 * 以一个所有的位都为1二进制数字开始，先左移它，然后用无符号右移操作对其进行右移，直至所有的二进制为都被移出位置，每移一步都要使用Integer.toBinaryString()展示结果
 */
public class Demo12 {
    public static void main(String[] args) {
        int h = -1;
        System.out.println(Integer.toBinaryString(h));
        h <<= 3;
        System.out.println(Integer.toBinaryString(h));
        for (int i = 0; i < 32; i++) {
            h >>>= 1;
            System.out.println(Integer.toBinaryString(h));
        }
    }
}
