package com.peng.chapter18.memento;

/**
 * @Auther lovely
 * @Create 2020-04-29 13:39
 * @Description 管理者
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
