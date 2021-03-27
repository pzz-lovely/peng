package one.chapter9.sieve;

import java.util.BitSet;

/**
 * @Auther lovely
 * @Create 2020-02-21 22:04
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Sieve {
    public static void main(String[] args) {
        int n = 2000000;
        long start = System.currentTimeMillis();
        BitSet b = new BitSet(n + 1);
        int count = 0;
        int i ;
        for(i = 2 ;i <= n;i++)
            b.set(i);

        i = 2;
        while (i * i <= n) {
            if (b.get(i)) {
                count++;
                int k = 2 * i;
                while (k <= n) {    //排除 4的倍数 6倍数 8的倍数 10的倍数
                    b.clear(k);
                    k += i;
                }
            }
            i++;
        }
        while (i <= n) {
            if(b.get(i))count++;
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println(count + " primes ");
        System.out.println((end - start) + " milliseconds");
    }
}
