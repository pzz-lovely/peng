package com.peng.chapter2.shop1.factor;

import com.peng.chapter2.shop1.cash.CashRebate;
import com.peng.chapter2.shop1.cash.CashSuper;

import java.lang.reflect.InvocationTargetException;

/**
 * @Auther lovely
 * @Create 2020-04-28 8:03
 * @Description todo
 */
public class CashFactory {
    public static CashSuper cashInstance(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (clazz == CashRebate.class) {
            return (CashSuper) clazz.getConstructor(double.class).newInstance(0.8);
        }
        return (CashSuper) clazz.getConstructor().newInstance();
    }
}
