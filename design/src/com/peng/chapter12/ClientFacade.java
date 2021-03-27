package com.peng.chapter12;

/**
 * @Auther lovely
 * @Create 2020-04-28 11:34
 * @Description 外观模式(Facade)，为子子系统中的一组提供一个一致的界面，此模式定义了一个高层接口，这个接口使得这一子系统更加容易使用
 */
public class ClientFacade {
    SubSystemOne one;
    SubSystemTwo two;
    SubSystemThree three;
    SubSystemFour four;

    public ClientFacade() {
        one = new SubSystemOne();
        two = new SubSystemTwo();
        three = new SubSystemThree();
        four = new SubSystemFour();
    }

    public void methodA(){
        System.out.println("方法A");
        one.methodOne();
        two.methodTwo();
        four.methodFour();
    }
    public void methodB(){
        System.out.println("方法A");
        one.methodOne();
        three.methodThree();
        four.methodFour();
    }
}
