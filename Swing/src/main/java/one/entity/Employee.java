package one.entity;

import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-02-20 11:46
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Employee {
    private String name;
    private double salary;
    private Date birthDay;

    public Employee(String name, double salary, Date birthDay) {
        this.name = name;
        this.salary = salary;
        this.birthDay = birthDay;
    }

    public Employee() {
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", birthDay=" + birthDay +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
