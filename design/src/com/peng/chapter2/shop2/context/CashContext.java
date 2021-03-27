package com.peng.chapter2.shop2.context;

import com.peng.chapter2.shop2.cash.CashReturn;
import com.peng.chapter2.shop2.cash.CashSuper;

/**
 * @Auther lovely
 * @Create 2020-04-28 8:30
 * @Description todo
 */
public class CashContext {
    private CashSuper cashSuper;

    public CashContext(String type) {
        if (type == "cashReturn") {
            cashSuper = new CashReturn();
        }
    }

    public double acceptCash(double money){
        return cashSuper.acceptCash(money);
    }
}
