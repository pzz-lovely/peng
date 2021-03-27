package array;

/**
 * @Auther lovely
 * @Create 2020-02-05 10:10
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class LotteryArray {
    public static void main(String[] args) {
        final int MAX = 10;
        int[][] odds = new int[MAX+1][];
        for (int n = 0; n <= MAX; n++) {
            //allocate triangular array  分配 里面的数组 allocate
            odds[n] = new int [n+1];
        }

        //fill triangular array 填充数组
        for (int n = 0; n < odds.length; n++) {
            for (int k = 1; k < odds[n].length; k++) {
                int lotteryOdds = 1;
                for (int i = 1; i < k; i++) {
                    lotteryOdds = lotteryOdds * (n-i+1) / i;
                }
                odds[n][k] = lotteryOdds;
            }
        }

        for (int[] row : odds) {
            for (int odd : row) {
                System.out.printf("%4d", odd);
            }
            System.out.println();
        }
    }
}
