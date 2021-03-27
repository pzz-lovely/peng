package com.peng.observer;


/**
 * @Author lovely
 * @Create 2020-09-11 16:41
 * @Description todo
 */
public interface ProductObserver {
    /**
     * 商品在添加的时候
     * @param product
     */
    void onPublished(Product product);

    /**
     * 商品价格
     * @param product
     */
    void onPriceChange(Product product);
}