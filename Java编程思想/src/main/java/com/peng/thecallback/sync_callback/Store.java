package com.peng.thecallback.sync_callback;

import java.util.Random;

/**
 * @Auther lovely
 * @Create 2020-07-04 11:03
 * @Description todo
 */
public class Store {
    private String name;

    public Store(String name) {
        this.name = name;
    }


    public String returnOrderGoodsInfo(OrderResult order) throws InterruptedException {
        String[] s = {"订购中...", "订购失败", "即将发货!", "运输途中...", "已在投递"};
        Random random = new Random();
        int temp = random.nextInt(5);
        String s1 = s[temp];
        Thread.sleep(3000);
        return order.getOrderResult(s1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
