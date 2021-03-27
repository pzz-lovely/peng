package com.peng.chapter9.prototype;

/**
 * @Auther lovely
 * @Create 2020-04-28 10:31
 * @Description
 * 原型模式(Prototype)，用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
 * 原型模式其实就是从一个对象创建再创建另外一个可定制的对象，二而且不需知道任何创建的细节
 */
public class Prototype implements Cloneable {
    private String id;
    private WorkExperience workExperience;


    public Prototype(String id, WorkExperience workExperience) {
        this.id = id;
        this.workExperience = workExperience;
    }

    public WorkExperience getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(WorkExperience workExperience) {
        this.workExperience = workExperience;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Prototype prototype = (Prototype) super.clone();
        prototype.setWorkExperience((WorkExperience) workExperience.clone());
        return prototype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
