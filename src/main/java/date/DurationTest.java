package date;

import java.time.Duration;
import java.time.Instant;

/**
 * @Auther lovely
 * @Create 2020-01-12 12:12
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 持续时间
 *  表示持续时间，例如两个时刻之间的时间，就想Instant类一样,Duration它以秒和纳为单位表示其时间
 *
 *  Duration对象 表示两个之间的一段时间Instant的对象。将Duration添加到java日期时间
 *  一个Duration实例是不可变的那么一旦它被创建，你不能改变它的值。
 *  创建Duration对象
 *  访问持续时间
 *      一个Duration内部由两个值组成
 *          持续时间的纳秒部分
 *          持续时间的第二部分
 *        纳秒部分代表的Duration是小于秒的部分。第二部分代表Duration大于一秒的部分
 *          使用
 *              getNano()   获取毫秒
 *              getSeconds() 获取秒
 *       还可以将整个时间间隔转换为Duration为其他单位:
 *          toNanos() 转换为纳秒
 *          toMillis() 转为毫秒
 *          toMinutes() 转为分钟
 *          toHours() 小时
 *          toDays() 天
 *      持续时间计算
 *          本Duration类包含了一套方法，你可以执行基于对计算Duration对象
 *             添加:
 *               plusNanos()
 *               plusMillis();
 *               plusMinutes()
 *               plusHours()
 *               plusDays()
 *             删除:
 *               minusMillis()
 *               minusSeconds()
 *               minusMinutes()
 *               minusHours()
 *               minusDays()
 */
public class DurationTest {
    public static void main(String[] args) throws InterruptedException {
        Instant first = Instant.now();
        System.out.println("first seconds "+first.getEpochSecond());
        //wait some time while something happens
        Thread.sleep(3000);
        Instant second = Instant.now();
        System.out.println("second seconds "+first.getEpochSecond());
        Duration duration = Duration.between(first, second);
        System.out.println("duration seconds "+duration.getSeconds()+" nano "+duration.getNano());

        System.out.println(duration.toMillis());

        //计算持续时间
        //添加的持续时间
        Duration addDuration = duration.plusDays(3);
        Duration delDuration = duration.minusMillis(1000);
        System.out.println("addDuration day = "+addDuration.toDays()+" delDuration millis = "+delDuration.toMillis());
    }
}
