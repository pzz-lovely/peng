package mldn.cascade.entity;

import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-03-21 22:07
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */

public class Employee {
    private String name;
    private Integer age;
    private String job;
    private Date birthday;

    private Dept dept;
    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }


    public Employee() {
    }


    public Employee(String name, Integer age, String job, Date birthday) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.birthday = birthday;
    }

    @Override
    public String  toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", birthday=" + birthday +
                ", dept=" + dept +
                '}';
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


}
