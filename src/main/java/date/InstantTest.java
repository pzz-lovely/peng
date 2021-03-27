package date;

import java.time.Instant;

/**
 * @Auther lovely
 * @Create 2020-01-12 11:57
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 *    Instant 表示时间线上的时间瞬间，在java7日期时间API中，时刻通常以自1月1日的毫秒数表示。1970年在java8中Instant该类表示时间点，此时间点表示字 1970年1月1日起的秒数和十亿之一秒
 *
 *    Instant在java日期时间代表在时间线上一个特定恶时刻，瞬时定义为距原点的偏移量(称为epoch)
 *      创建Instant对象 Instant.now();
 *      访问瞬间的时间
 *          一个Instant对象内部包含两个字段，其中包含以表示的时间Instant:
 *              字时代以来的秒数
 *              纳秒
 *                getEpochSecond() 访问秒
 *                getNano() 访问纳秒
 *       即时计算
 *          instant类也有几种方法可以用来制作相对于计算Instant
 */
public class InstantTest {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println("秒 : " + instant.getEpochSecond());
        System.out.println("纳秒 : " + instant.getNano());
    }
}
