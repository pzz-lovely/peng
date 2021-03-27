package com.peng.chapter13.build;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:55
 * @Description

 * 将一个复杂对象的构建与他的表示分离，使用同样的构建过程可以创建不同的表示
 */
public class BuilderTest {
    public static void main(String[] args) {
        Director director = new Director();
        Builder builder = new ConcreteBuilderA();
        director.construct(builder);

        Product product = builder.getResult();
        product.show();
    }
}
