package com.peng.chapter2.strategy;

import com.peng.chapter2.strategy.context.StrategyContext;

/**
 * @Auther lovely
 * @Create 2020-04-28 8:24
 * @Description 策略模式（Strategy）它定义了算法家族，分别封装起来，让它们之间可以相互替换，此模式让算法的变化，不会影响到使用算法的客户
 */
public class StrategyTest {
    public static void main(String[] args) {
        StrategyContext context = new StrategyContext(new ConcreteStrategyB());
        context.algorithmInterface();
        context.replaceStrategy(new ConcreteStrategyC());
        context.algorithmInterface();
    }
}
