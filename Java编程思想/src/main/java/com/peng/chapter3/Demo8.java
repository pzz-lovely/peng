package com.peng.chapter3;

/**
 * @Auther lovely
 * @Create 2020-06-30 19:45
 * @Description 展示用十六进制和八进制来操作Long值，用Long.toBinaryString()来展示结果
 */
public class Demo8 {
    public static void main(String[] args) {
        long n1 = 0xffff; //hexadecimal
        long n2 = 077777; // octal
        System.out.println("long n1 in hex = "+ Long.toBinaryString(n1));
        System.out.println("long n2 in oct = "+ Long.toBinaryString(n2));
    }
}
