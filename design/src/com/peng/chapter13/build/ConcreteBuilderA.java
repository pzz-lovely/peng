package com.peng.chapter13.build;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:51
 * @Description todo
 */
public class ConcreteBuilderA extends Builder {
    @Override
    public void buildPartA() {
        product.add("A");
    }

    @Override
    public void buildPartB() {
        product.add("B");
    }
}
