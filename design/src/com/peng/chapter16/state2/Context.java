package com.peng.chapter16.state2;

/**
 * @Auther lovely
 * @Create 2020-04-29 11:07
 * @Description todo
 */
public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void request(){
        state.handler(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
