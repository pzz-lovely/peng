package com.peng.observer;

import sun.invoke.empty.Empty;

import java.util.*;

/**
 * @Author lovely
 * @Create 2020-09-11 16:43
 * @Description todo
 */
public class Store {
    private List<ProductObserver> observers = new ArrayList<>();

    private Map<String, Product> products = new HashMap<>();


    public void addObserver(ProductObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(ProductObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * 添加商品的时候 keySet
     * @param name
     * @param price
     */
    public void addNewProduct(String name, double price) {
        Product p = new Product(name, price);
        products.put(p.getName(), p);

        observers.forEach(o -> o.onPublished(p));
    }


    public void setProductPrice(String name, double price) {
        Product p = products.get(name);

        p.setPrice(price);

        observers.forEach(o -> o.onPriceChange(p));
    }
}