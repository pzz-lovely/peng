package mldn.random;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther lovely
 * @Create 2020-03-24 18:16
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 彩票工具类
 */
public class LotteryTicket {
    private int index;//手工生成索引
    private int[] data; //保存最终生成的彩票数据
    private Random rand = new Random();
    public LotteryTicket(){
        this.data = new int[7];
    }

    public void random(){//随机生成彩票数据
        while (this.index < this.data.length) {
            int code = this.rand.nextInt(37);
            if (this.isExists(code)) {
                this.data[index++] = code;
            }
        }
    }

    private boolean isExists(int code) {
        if (code == 0) {
            return false;
        }
        for (int temp : this.data) {
            if (temp == code) {
                return false;
            }
        }
        return true;
    }

    public int[] getData(){
        Arrays.sort(data);
        return data;
    }
}
