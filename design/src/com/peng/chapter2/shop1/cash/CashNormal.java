package com.peng.chapter2.shop1.cash;

/**
 * @Auther lovely
 * @Create 2020-04-28 8:02
 * @Description 正常返回原价
 */
public class CashNormal extends CashSuper {

    @Override
    public double acceptCash(double money) {
        return money;
    }
}
