package com.peng.chapter14.observer2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther lovely
 * @Create 2020-04-29 9:32
 * @Description todo
 */
public class Boss implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String action;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyColleagues() {
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
