package com.peng.chapter3;

/**
 * @Auther lovely
 * @Create 2020-06-30 19:48
 * @Description 分别显示float和double指数计数法所能表示的最大和最小的数字
 */
public class Demo9 {
    public static void main(String[] args) {
        float minF = Float.MIN_VALUE;
        System.out.println("min float = " + minF);
        float maxF = Float.MAX_VALUE;
        System.out.println("max float = " + maxF);

        double minD = Double.MIN_VALUE;
        System.out.println("min double = " + minD);
        double maxD = Double.MAX_VALUE;
        System.out.println("max double = " + maxD);


    }
}
