package com.peng.chapter17.adapter2;

/**
 * @Auther lovely
 * @Create 2020-04-29 11:40
 * @Description todo
 */
public abstract class Player {
    protected  String name;

    public Player(String name) {
        this.name = name;
    }

    public abstract void attack();
    public abstract void defense();
}
