package com.peng.observer;

/**
 * @Author lovely
 * @Create 2020-09-11 17:10
 * @Description todo
 */
public class Customer implements ProductObserver {

    @Override
    public void onPublished(Product product) {
        System.err.println("[Customer] on product published: " + product);
    }

    @Override
    public void onPriceChange(Product product) {
        System.err.println("[Customer] on product published: " + product);
    }
}