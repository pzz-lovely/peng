package com.peng.chapter13.build;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:51
 * @Description todo
 */
public class ConcreteBuilderB extends Builder {
    @Override
    public void buildPartA() {
        product.add("X");
    }

    @Override
    public void buildPartB() {
        product.add("Y");
    }
}
