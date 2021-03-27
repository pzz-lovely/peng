package com.peng.chapter12;

/**
 * @Auther lovely
 * @Create 2020-04-28 11:41
 * @Description
 * 增加外观Facade可以提供一个 简单的接口，减少它们之间的依赖
 */
public class FacadeTest {
    public static void main(String[] args) {
        ClientFacade facade = new ClientFacade();
        facade.methodA();
    }
}
