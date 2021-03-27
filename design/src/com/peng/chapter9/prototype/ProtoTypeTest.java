package com.peng.chapter9.prototype;

import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-04-28 10:34
 * @Description
 *
 * 浅复制，被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用都仍然指向原来对象。
 * 深复制把引用对象的变量指向复制过的新对象，而不是原有的被引用的对象。
 */
public class ProtoTypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*如果字段是值类型，则对该字段执行逐位复制，如果字段是引用类型，则复制引用但不复制引用的对象，因此，元素对象及其副本 引用同一对象*/
        /*WorkExperience workExperience = new WorkExperience(new Date());
        Prototype prototype = new Prototype("1", workExperience);
        System.out.println("clone before :" + prototype.getWorkExperience().getWorkDate());
        Prototype cloneProto = (Prototype) prototype.clone();
        System.out.println("clone after :" + cloneProto.getWorkExperience().getWorkDate());
        System.out.println(prototype.getWorkExperience() == cloneProto.getWorkExperience());*/

        /*深复制 在跟 WorkExperience实现了 Cloneable接口后*/
        WorkExperience workExperience = new WorkExperience(new Date());
        Prototype prototype = new Prototype("1", workExperience);
        System.out.println("clone before :" + prototype.getWorkExperience().getWorkDate());
        Prototype cloneProto = (Prototype) prototype.clone();
        System.out.println("clone after :" + cloneProto.getWorkExperience().getWorkDate());
        System.out.println(prototype.getWorkExperience() == cloneProto.getWorkExperience());

    }
}
