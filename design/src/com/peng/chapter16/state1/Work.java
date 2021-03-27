package com.peng.chapter16.state1;

/**
 * @Auther lovely
 * @Create 2020-04-29 10:26
 * @Description todo
 */
public class Work {
    private State state;

    private double hour;
    private boolean finish = false;

    public Work(){
        state = new ForenoonState();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public void writeProgram(){
        state.writeProgram(this);
    }
}
