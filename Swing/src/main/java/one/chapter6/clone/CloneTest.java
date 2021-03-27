package one.chapter6.clone;

import java.util.Date;

/**
 * @Auther lovely
 * @Create 2020-02-19 19:13
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        //
        Employee employee = new Employee("0.0", 1000, new Date());
        employee.setHireDay(2020, 2, 19);
        Employee copy = employee.clone();
        System.out.println(employee == copy);
        System.out.println(employee.getHireDay() == copy.getHireDay());

        copy.setSalary(10);/*Runnable*/
        copy.setHireDay(2000, 10, 10);
        System.out.println("employee = " + employee);
        System.out.println("Copy = "+employee);
    }

}
