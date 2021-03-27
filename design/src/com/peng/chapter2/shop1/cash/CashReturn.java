package com.peng.chapter2.shop1.cash;

/**
 * @Auther lovely
 * @Create 2020-04-28 8:00
 * @Description 返利
 */
public class CashReturn extends CashSuper {

    @Override
    public double acceptCash(double money) {
        if (money == 200) {
            return money - 10;
        } else if (money == 600) {
            return money - 100;
        }
        return money;
    }
}
