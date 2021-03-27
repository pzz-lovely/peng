package com.peng.chapter3;

/**
 * @Auther lovely
 * @Create 2020-06-30 20:14
 * @Description 编写一个方法，它以二进制形式显示char类型的值。使用多个不同的字符来展示它
 */
public class Demo13 {
    public static void main(String[] args) {
        char c = 'a';
        System.out.println(Integer.toBinaryString(c));
        c = 'b';
        System.out.println(Integer.toBinaryString(c));
        c = 'c';
        System.out.println(Integer.toBinaryString(c));
        c = 'd';
        System.out.println(Integer.toBinaryString(c));
        c +=1;
        System.out.println(Integer.toBinaryString(c));
        c = 'A';
        System.out.println(Integer.toBinaryString(c));
        for(int i = 0; i < 26; i++) {
            c +=1;
            System.out.println(Integer.toBinaryString(c));
        }
    }
}
