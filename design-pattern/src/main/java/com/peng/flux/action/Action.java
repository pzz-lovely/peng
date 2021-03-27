package com.peng.flux.action;

/**
 * @author lovely
 * @create 2021-03-02 16:57
 * @description
 */
public abstract class Action {
    private final ActionType type;

    public Action(ActionType type) {
        this.type = type;
    }

    public ActionType getType() {
        return type;
    }
}