package com.peng.chapter18.memento;

/**
 * @Auther lovely
 * @Create 2020-04-29 13:39
 * @Description 备忘录
 * 将要保存的细节给封装到了 Memento中了，那一天要更改保存的细节也不会影响到客户端
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
