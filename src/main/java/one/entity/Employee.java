package one.entity;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private double money;
    private int age;

    public Employee(String name, double money, int age) {
        this.name = name;
        this.money = money;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
