package com.peng.jdk9.privateinterface;

/**
 * @author lovely
 * @create 2021-03-11 11:01
 * @description
 */
public interface PrivateInterface {
    // jdk7 ： 只能声明全局常量(public static final)和抽象方法(public abstract)
    void method1();

    // jdk8：支持 静态方法 和 默认方法
    static void method2(){
        System.out.println("method2");
    }

    default void method3(){
        System.out.println("method3");
    }

    // jdk9：声明私有方法
    private void method4(){
        System.out.println("私有方法");
    }
}