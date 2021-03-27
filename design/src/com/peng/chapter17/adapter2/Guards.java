package com.peng.chapter17.adapter2;

/**
 * @Auther lovely
 * @Create 2020-04-29 11:42
 * @Description 中锋
 */
public class Guards extends Player {

    public Guards(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("后锋 " + name + "进攻");
    }

    @Override
    public void defense() {
        System.out.println("后锋 " + name + "防守");
    }
}
