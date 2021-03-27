package com.peng.jdk8.lambda;

/**
 * @Auther lovely
 * @Create 2021-03-10 16:36
 * @Description todo
 */
@FunctionalInterface
public interface FunctionalInterfaceDemo {
    int computed(int num1, int num2);

    default int add(int num1){
        return this.computed(num1, 10);
    }

    static int reduce(int num1){
        return 0;
    }
}
