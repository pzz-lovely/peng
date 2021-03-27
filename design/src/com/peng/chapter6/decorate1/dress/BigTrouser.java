package com.peng.chapter6.decorate1.dress;

/**
 * @Auther lovely
 * @Create 2020-04-28 9:47
 * @Description todo
 */
public class BigTrouser extends Finery {

    public BigTrouser(Finery finery) {
        super(finery);
    }

    @Override
    public void show() {
        System.out.println("大裤子");
        super.show();
    }
}
