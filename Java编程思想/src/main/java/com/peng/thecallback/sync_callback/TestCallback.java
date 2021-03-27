package com.peng.thecallback.sync_callback;

/**
 * @Auther lovely
 * @Create 2020-07-04 11:09
 * @Description todo
 */
public class TestCallback {
    public static void main(String[] args) {
        Store store = new Store("小卖部");
        SyncBuyer buyer = new SyncBuyer(store, "小明","双节棍");
        try {
            System.out.println(buyer.orderGoods());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
