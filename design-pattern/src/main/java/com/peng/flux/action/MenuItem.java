package com.peng.flux.action;

/**
 * @author lovely
 * @create 2021-03-02 17:03
 * @description
 */
public enum MenuItem {
HOME("Home"),PRODUCTS("Products"), COMPANY("Company"),
    ;

    private String title;

    MenuItem(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "title='" + title + '\'' +
                '}';
    }
}