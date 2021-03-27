package com.peng.thecallback.sync_callback;

import java.util.Random;

/**
 * @Auther lovely
 * @Create 2020-07-04 11:05
 * @Description
 * 同步，顾客在商定预定商品，商店通知顾客预定情况
 */
public class SyncBuyer implements OrderResult {

    private Store store;
    // 购物姓名
    private String buyerName;

    private String goodsName;


    public SyncBuyer(Store store, String buyerName, String goodsName) {
        this.store = store;
        this.buyerName = buyerName;
        this.goodsName = goodsName;
    }

    public void myFeeling(){
        String[] s = {"有点小激动", "很期待!", "希望是个好货!"};
        Random random = new Random();
        int temp = random.nextInt(3);
        System.out.println("我是" + this.buyerName + "，我现在的感觉 " + s[temp]);
    }


    public String
    orderGoods() throws InterruptedException {
        String goodsState = store.returnOrderGoodsInfo(this);
        System.out.println(goodsState);
        myFeeling();    //测试同步还是异步，同步需要等待，异步无需等待
        return goodsState;
    }

    @Override
    public String getOrderResult(String state) {
        return "在"+this.store.getName()+"商店预购的"+this.getGoodsName()+"玩具,目前的预订状态是: " + state;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
