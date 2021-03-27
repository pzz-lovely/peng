package com.peng.chapter4;

/**
 * @Auther lovely
 * @Create 2020-06-30 20:44
 * @Description 斐波那契
 */
public class Demo9 {

    static int fib(int n) {
        if(n < 2)
            return 1;
        return (fib(n - 2) + fib(n - 1));
    }

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        System.out.println("First " + k + " Fibonacci number(s): ");
        for(int i = 0; i < k; i++)
            System.out.println(fib(i));
    }
}
