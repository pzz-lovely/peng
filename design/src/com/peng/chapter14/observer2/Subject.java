package com.peng.chapter14.observer2;

/**
 * @Auther lovely
 * @Create 2020-04-29 9:28
 * @Description todo
 */
public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyColleagues();
    String getAction();

    void setAction(String action);
}
