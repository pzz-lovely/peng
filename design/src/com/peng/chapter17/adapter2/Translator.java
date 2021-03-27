package com.peng.chapter17.adapter2;

/**
 * @Auther lovely
 * @Create 2020-04-29 11:50
 * @Description todo
 */
public class Translator extends Player {
    //外籍中锋
    private ForeginCenter wjzf = new ForeginCenter();

    public Translator(String name) {
        super(name);
        wjzf.setName(name);
    }

    @Override
    public void attack() {
        wjzf.进攻();
    }

    @Override
    public void defense() {
        wjzf.防守();
    }
}
