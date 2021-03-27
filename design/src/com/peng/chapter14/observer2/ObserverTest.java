package com.peng.chapter14.observer2;


/**
 * @Auther lovely
 * @Create 2020-04-29 9:18
 * @Description 双耦合模式
 */
public class ObserverTest {
    public static void main(String[] args) {
        Boss boss = new Boss();
        StockObserver stockObserver1 = new StockObserver("0.0", boss);
        StockObserver stockObserver2 = new StockObserver("0.1", boss);
        boss.attach(stockObserver1);
        boss.attach(stockObserver2);

        boss.setAction("监视情况");
        //通知同事
        boss.notifyColleagues();
    }
}
