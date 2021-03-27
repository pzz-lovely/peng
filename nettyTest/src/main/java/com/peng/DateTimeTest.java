package com.peng;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author lovely
 * @Create 2020-09-20 12:15
 * @Description todo
 */
public class DateTimeTest {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();

        Date date = new Date();
        System.out.println(dateTime);
        System.out.println(date);
    }
}