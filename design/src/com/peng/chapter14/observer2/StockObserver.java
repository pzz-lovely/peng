package com.peng.chapter14.observer2;

/**
 * @Auther lovely
 * @Create 2020-04-29 9:37
 * @Description 解耦合的情况下。
 * 观察模式又叫做 发布-订阅模式(publish/Subscribe)模式
 * 观察者模式定义了一种 一对多的 依赖关系，让多个观察对象同时监听某一个主题的对象，这个主题对象在状态发生变化时，会通知所有观察者对象，使他们能够自动更新自己
 */
public class StockObserver extends Observer {
    public StockObserver(String name, Subject sub) {
        super(name, sub);
    }

    @Override
    public void update() {
        System.out.println(sub.getAction() + " say closing stock quotation," + name + "继续工作");
    }
}
