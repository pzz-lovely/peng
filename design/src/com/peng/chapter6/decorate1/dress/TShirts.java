package com.peng.chapter6.decorate1.dress;

/**
 * @Auther lovely
 * @Create 2020-04-28 9:45
 * @Description 具体的装饰类
 */
public class TShirts extends Finery {

    public TShirts(Finery finery) {
        super(finery);
    }

    @Override
    public void show() {
        System.out.println("T恤");
        super.show();
    }
}
