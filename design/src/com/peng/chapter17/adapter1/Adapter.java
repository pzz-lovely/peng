package com.peng.chapter17.adapter1;

/**
 * @Auther lovely
 * @Create 2020-04-29 11:34
 * @Description todo
 */
public class Adapter extends Target {
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
