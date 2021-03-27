package com.peng.flux.action;

import java.awt.*;

/**
 * @author lovely
 * @create 2021-03-02 17:02
 * @description
 */
public class MenuAction extends Action {
    private final MenuItem menuItem;


    public MenuAction( MenuItem menuItem) {
        super(ActionType.MENU_ITEM_SELECTED);
        this.menuItem = menuItem;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

}