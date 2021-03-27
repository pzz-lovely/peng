package com.peng.chapter2.strategy.context;

import com.peng.chapter2.strategy.Strategy;

/**
 * @Auther lovely
 * @Create 2020-04-28 8:22
 * @Description context上下文，用一个ConcreteStrategy来配置，维护一个对Strategy对象的引用
 */
public class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void algorithmInterface(){
        strategy.algorithmInterface();
    }

    public void replaceStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
