package com.peng.dynamic;

/**
 * @Author lovely
 * @Create 2020-12-24 14:14
 * @Description todo
 */
public class Demo {
    public static void main(String[] args) {
        // 子类的实现 父类的引用

        Animal animal = new Goose();
        System.out.println(animal.run());
        Bird bird = (Bird) animal;
        System.out.println(bird.fly());

        // 因为是子类的实现 最终调用的方法是 子类实现的方法
        // 而父类没有的方法，子类有，因为是父类的类型(引用)，不能调用父类没有的方法
        Animal cat = new Cat();
        System.out.println(cat.run());


        // 组合的方式实现多态
        Wing wing = new Wing("红色");
        Eagle eagle = new Eagle();
        eagle.setWing(wing);
        System.out.println(eagle.fly());

    }
}