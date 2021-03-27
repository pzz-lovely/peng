package com.peng.chapter6.decorate1.dress;

/**
 * @Auther lovely
 * @Create 2020-04-28 9:41
 * @Description 服饰类 抽象装饰角色
 */
public class Finery {
    protected Finery finery;

    public Finery(Finery finery) {
        this.finery = finery;
    }

    //装饰
    public void decorator(Finery finery){
        this.finery = finery;
    }

    public void show(){
        if (finery != null) {
            finery.show();
        }

    }
}
