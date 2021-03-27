package com.peng.observer;

/**
 * @Author lovely
 * @Create 2020-09-11 17:06
 * @Description todo
 */
public class Admin implements ProductObserver {


    @Override
    public void onPublished(Product product) {
        System.err.println("[Admin] on product published: " + product);
    }

    @Override
    public void onPriceChange(Product product) {
        System.err.println("[Admin] on product price changed: " + product);
    }
}