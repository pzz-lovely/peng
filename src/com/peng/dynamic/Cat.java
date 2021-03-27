package com.peng.dynamic;

/**
 * @Author lovely
 * @Create 2020-12-24 14:15
 * @Description todo
 */
public class Cat extends AbstractAnimal implements Animal {

    @Override
    public String run() {
        return "猫 跑";
    }
}