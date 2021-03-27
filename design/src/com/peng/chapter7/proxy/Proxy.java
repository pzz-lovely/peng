package com.peng.chapter7.proxy;

/**
 * @Auther lovely
 * @Create 2020-04-28 10:09
 * @Description todo
 */
public class Proxy {
    public static void run(Pursuit pursuit) {
        System.out.println(pursuit.getName() + "送出的礼物" + pursuit.giftGiving());
    }
}
