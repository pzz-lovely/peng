package com.peng.chapter14.observer1;

/**
 * @Auther lovely
 * @Create 2020-04-29 9:12
 * @Description 同事
 */
public class StockObserver {
    private String name;
    private Secretary sub;

    public StockObserver(String name, Secretary sub) {
        this.name = name;
        this.sub = sub;
    }

    public void update(){
        System.out.println(sub.getAction() + " say closing stock quotation," + name + "继续工作");
    }
}
