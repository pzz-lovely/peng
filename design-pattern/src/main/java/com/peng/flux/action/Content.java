package com.peng.flux.action;

/**
 * @author lovely
 * @create 2021-03-02 16:58
 * @description
 */
public enum Content {
    PRODUCTS("Products - This page lists the company's products."),
    COMPANY("Company - This page displays information about the company."),;


    private final String title;

    Content(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                '}';
    }
}