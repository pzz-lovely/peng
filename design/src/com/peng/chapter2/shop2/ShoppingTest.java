package com.peng.chapter2.shop2;

import com.peng.chapter2.shop2.context.CashContext;

import java.lang.reflect.InvocationTargetException;

/**
 * @Auther lovely
 * @Create 2020-04-28 7:56
 * @Description 策略模式(Strategy)
 * 它定义了算法家族，分别封装起来，让它们之间可以相互转换，此模式让算法的变化，不会影响到使用算法的 客户。
 */
public class ShoppingTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CashContext cashContext = new CashContext("cashReturn");
        System.out.println(cashContext.acceptCash(1000));
    }
}
