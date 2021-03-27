package com.peng.chapter14.observer1;

/**
 * @Auther lovely
 * @Create 2020-04-29 9:18
 * @Description 双耦合模式
 */
public class ObserverTest {
    public static void main(String[] args) {
        Secretary secretary = new Secretary();
        StockObserver stockObserver1 = new StockObserver("0.0", secretary);
        StockObserver stockObserver2 = new StockObserver("0.1", secretary);
        secretary.add(stockObserver1);
        secretary.add(stockObserver2);

        secretary.setAction("老板回来了");
        //通知同事
        secretary.notifyColleagues();
    }
}
