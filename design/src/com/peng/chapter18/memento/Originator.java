package com.peng.chapter18.memento;

/**
 * @Auther lovely
 * @Create 2020-04-29 13:38
 * @Description 发起人
 */
public class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Memento createMemento(){
        return (new Memento(state));
    }

    public void setMemento(Memento memento) {
        state = memento.getState();
    }

    public void show(){
        System.out.println("State = " + state);
    }


}
