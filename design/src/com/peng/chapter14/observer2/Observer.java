package com.peng.chapter14.observer2;

/**
 * @Auther lovely
 * @Create 2020-04-29 9:29
 * @Description todo
 */
public abstract class Observer {
    protected String name;
    protected Subject sub;

    public Observer(String name, Subject sub) {
        this.name = name;
        this.sub = sub;
    }

    public abstract void update();

}
