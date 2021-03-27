package com.peng.chapter6.decorate1;

import com.peng.chapter6.decorate1.dress.BigTrouser;
import com.peng.chapter6.decorate1.dress.Finery;
import com.peng.chapter6.decorate1.dress.TShirts;

/**
 * @Auther lovely
 * @Create 2020-04-28 9:48
 * @Description 装饰器模式是为已有的功能动态添加更多功能的一种方式
 * 总结：
 *     构建角色(Component)：
 *     具体构建角色(ConcreteComponent)：Person扮演
 *     抽象装饰角色(Decorator) 有Finery扮演
 *     具体装饰角色(ConcreteDecorator)由BigTrouser,TShirt扮演。
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Finery finery = new Finery(new BigTrouser(new TShirts(null)));
        /*BigTrouser trouser = new BigTrouser();
        TShirts shirts = new TShirts();

        shirts.decorator(trouser);
        finery.decorator(shirts);*/
        Person person = new Person("0.0",finery);
        person.show();
    }
}
