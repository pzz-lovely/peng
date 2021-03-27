package com.peng.chapter17.adapter1;

/**
 * @Auther lovely
 * @Create 2020-04-29 11:36
 * @Description
 * 适配器模式(Adapter)，将一个类的接口转换成客户所希望的另外一个接口。Adapter模式使得原本由于借口不兼容而不能一起工作的哪些类可以一起工作。
 * 类适配器 和对象适配器]
 * 由于java不支持多继承  对象适配器
 */
public class AdapterTest {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}
