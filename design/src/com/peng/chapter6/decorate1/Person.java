package com.peng.chapter6.decorate1;

import com.peng.chapter6.decorate1.dress.Finery;

/**
 * @Auther lovely
 * @Create 2020-04-28 9:39
 * @Description todo
 */
public class Person {
    private String name;
    private Finery finery;

    public Person(String name, Finery finery) {
        this.name = name;
        this.finery = finery;
    }

    public void show(){
        System.out.println("my name is " + name);
        finery.show();
    }
}
