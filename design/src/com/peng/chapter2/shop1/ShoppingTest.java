package com.peng.chapter2.shop1;

import com.peng.chapter2.shop1.cash.CashRebate;
import com.peng.chapter2.shop1.cash.CashSuper;
import com.peng.chapter2.shop1.factor.CashFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @Auther lovely
 * @Create 2020-04-28 7:56
 * @Description 策略模式(Strategy)
 * 它定义了算法家族，分别封装起来，让它们之间可以相互转换，此模式让算法的变化，不会影响到使用算法的 客户。
 */
public class ShoppingTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CashSuper cashSuper = CashFactory.cashInstance(CashRebate.class);
        System.out.println(cashSuper.acceptCash(1000));
    }
}
