package com.peng.chapter2.shop2.cash;

/**
 * @Auther lovely
 * @Create 2020-04-28 7:58
 * @Description 打折
 */
public class CashRebate extends CashSuper {

    private double discount;


    public CashRebate(double discount) {
        this.discount = discount;
    }

    @Override
    public double acceptCash(double money) {
        return money * discount;
    }

}
