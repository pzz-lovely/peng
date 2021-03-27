package com.peng.chapter4;

/**
 * @Auther lovely
 * @Create 2020-06-30 20:20
 * @Description 吸血鬼数字是指位数为偶数的数字，可以由一对数字相乘而得到,而这对数字各包含乘积一半的数字，其中从最初的数字中可以任意排序
 * 1260 = 12 * 60
 * 1827 = 12 * 87
 */
public class Demo10 {
    public static void main(String[] args) {
        for (int i = 1001; i < 9999; i++) {
            productTest(i, com(a(i), b(i)), com(c(i), d(i)));
           /* productTest(i, com(a(i), b(i)), com(d(i), c(i)));
            productTest(i, com(a(i), c(i)), com(b(i), d(i)));
            productTest(i, com(a(i), c(i)), com(d(i), b(i)));
            productTest(i, com(a(i), d(i)), com(b(i), c(i)));
            productTest(i, com(a(i), d(i)), com(c(i), b(i)));
            productTest(i, com(b(i), a(i)), com(c(i), d(i)));
            productTest(i, com(b(i), a(i)), com(d(i), c(i)));
            productTest(i, com(b(i), c(i)), com(d(i), a(i)));
            productTest(i, com(b(i), d(i)), com(c(i), a(i)));
            productTest(i, com(c(i), a(i)), com(d(i), b(i)));
            productTest(i, com(c(i), b(i)), com(d(i), a(i)));*/
        }
    }


    // 求出第一位数
    static int a(int i) {
        return  i /1000;
    }
    // 求出第二位数
    static int b(int i) {
        return (i%1000)/100;
    }

    // 求出第三位数
    static int c(int i) {
        return ((i % 1000) % 100) / 10;
    }

    // 求出第四位数
    static int d(int i) {
        return ((i * 1000) % 100) % 10;
    }

    // 前一位数和后一位数的相加
    static int com(int m, int n) {
        return (m * 10) + n;
    }

    static void productTest(int i, int m, int n) {
        if(m *n == i) System.out.println(i +" = "+ m+" * "+n);
    }

}
