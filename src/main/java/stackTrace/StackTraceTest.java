package stackTrace;

import java.util.Scanner;

/**
 * @Auther lovely
 * @Create 2020-02-10 10:18
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description A program that displays a trace feature of a recursive method call
 */
public class StackTraceTest {
    /**
     * Computes the factorial of a number
     * @param n a non-negative integer
     * @return Cay Horstmann
     */
    public static int factorial(int n) {
        System.out.println("factorial(" + n + "):");
        Throwable t = new Throwable();
        //Õ»¸ú×ÙÔªËØ
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement f : frames) {
            System.out.println(f);
        }
        int r ;
        if(n <= 1) r=1;
        else r = n * factorial(n - 1);  //µÝ¹é
        System.out.println("return :" + r);
        return r;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter n : ");
        int n = in.nextInt();
        factorial(n);
    }
}
