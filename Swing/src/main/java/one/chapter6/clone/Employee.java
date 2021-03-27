package one.chapter6.clone;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Auther lovely
 * @Create 2020-02-19 19:05
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Employee implements Cloneable {
    private String name;
    private double salary;
    private Date hireDay;


    @Override
    protected Employee clone() throws CloneNotSupportedException {
        // 开辟新内存地址  一块内存 一块新的内存
        Employee employee = (Employee) super.clone();
        //clone mutable fields
        // employee.setHireDay ((Date) hireDay.clone());
        return employee;
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
        double raise = salary * salary / 100;
        salary += raise;
    }



    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }


    public void setHireDay(int year, int month, int day) {
        Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
        //Example of instance field mutation
        hireDay.setTime(newHireDay.getTime());
    }

    public Employee(String name, double salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
