package mldn.random;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther lovely
 * @Create 2020-03-24 18:05
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class RandomDemo {
    public static void main(String[] args) {
        LotteryTicket ticket = new LotteryTicket();
        ticket.random();
        int[] data = ticket.getData();
        System.out.println(Arrays.toString(data));
    }
}
