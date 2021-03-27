package com.peng.chapter17.adapter2;

/**
 * @Auther lovely
 * @Create 2020-04-29 11:44
 * @Description todo
 */
public class AdapterTest {
    public static void main(String[] args) {
        Player b = new Forwards("巴蒂尔");
        b.attack();

        Player m = new Guards("麦克格雷迪");
        m.attack();
        //假设姚明不懂英语，他这时候是听不懂命令的
        Player ym = new Translator("姚明");
        ym.attack();
        ym.defense();


    }
}
