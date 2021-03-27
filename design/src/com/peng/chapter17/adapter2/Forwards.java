package com.peng.chapter17.adapter2;

/**
 * @Auther lovely
 * @Create 2020-04-29 11:42
 * @Description todo
 */
public class Forwards extends Player {

    public Forwards(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("前锋 " + name + "进攻");
    }

    @Override
    public void defense() {
        System.out.println("前锋 " + name + "防守");
    }
}
