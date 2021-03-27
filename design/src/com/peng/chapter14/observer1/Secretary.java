package com.peng.chapter14.observer1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther lovely
 * @Create 2020-04-29 9:12
 * @Description 前台小妹
 */
public class Secretary {
    private List<StockObserver> observers = new ArrayList<>();
    private String action;


    public void add(StockObserver stockObserver){
        observers.add(stockObserver);
    }

    public void notifyColleagues(){
        observers.forEach(e -> {
            e.update();
        });
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
