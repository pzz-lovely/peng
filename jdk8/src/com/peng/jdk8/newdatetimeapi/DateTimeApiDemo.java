package com.peng.jdk8.newdatetimeapi;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Set;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author lovely
 * @create 2021-03-11 10:09
 * @description
 */
public class DateTimeApiDemo {
    public static void main(String[] args) {

    }

    public static void instantDemo() {
        // 测试执行一个 new 操作使用的时间（纳秒值）
        Instant begin = Instant.now();
        for (int i = 0; i < 10000; i++) {
            System.out.println(Integer.valueOf(i).byteValue());
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(begin, end).toNanos());
    }

    public static void localDateTimeDemo() {
        LocalDate localDate = LocalDate.of(2021, Month.MARCH, 22);
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        // 获取现在的时间，这是一个静态方法
        LocalDate now = LocalDate.now();

// 每个实例可以获取它们的 part 信息,如获取年
        int year = localDate.getYear();

// 可以修改 part 信息，这将返回一个新对象，如增加一年
        LocalDate localDatePlus = localDate.plusYears(1);

// 设置 part 信息，也会返回新的对象，如设置为 2017 年
        LocalDate localDateWithYear = localDate.withYear(2017);

// 比较两个日期 isAfter,isBefore
        boolean after = localDate.isAfter(LocalDate.now());

// 格式化日期时间
// yyyy-MM-dd
        System.out.println(now.format(DateTimeFormatter.ISO_DATE));
// yyyy-MM-ddTHH:mm:ss
        System.out.println(now.format(DateTimeFormatter.ISO_DATE_TIME));
// yyyy-MM-dd HH:mm:ss
        System.out.println(now.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

// 日期解析
        System.out.println(LocalDate.parse("2019-09-22"));
        System.out.println(LocalDateTime.parse("2019-09-22T21:05:22"));
        System.out.println(LocalDateTime.parse("2019-09-22 21:05:22", DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }

    public static void zoneIdDemo(){
        ZoneId zoneId = ZoneId.systemDefault();
        Set<String> availableZoneId = ZoneId.getAvailableZoneIds();
    }

    public static void periodDurationDemo(){
        // Period，Duration 可以视为一组，用于计算时间间隔
        // 创建一个两周的间隔
        Period periodWeeks = Period.ofWeeks(2);

// 一年三个月零二天的间隔
        Period custom = Period.of(1, 3, 2);

// 一天的时长
        Duration duration = Duration.ofDays(1);

// 计算2015/6/16 号到现在 2019/09/22 过了多久，它这个把间隔分到每个 part 了
        LocalDate now = LocalDate.now();
        LocalDate customDate = LocalDate.of(2015, 6, 16);
        Period between = Period.between(customDate, now);
// 结果为 4:3:6 即过去了 4年3个月6天了
        System.out.println(between.getYears()+":"+between.getMonths()+":"+between.getDays());

// 比较两个瞬时的时间间隔
        Instant begin = Instant.now();
        Instant end = Instant.now();
        Duration.between(begin,end);

// 同样可以修改 part 信息和设置 part 信息，都是返回新的对象来表示设置过的值，原来的对象不变
        Period plusDays = between.plusDays(1);
        Period withDays = between.withDays(4);
    }
}