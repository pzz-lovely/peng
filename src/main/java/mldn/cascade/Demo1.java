package mldn.cascade;

import mldn.cascade.entity.Employee;

/**
 * @Auther lovely
 * @Create 2020-03-21 22:08
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Demo1 {
    private static final String ECHO_DATA = "name:0.0|job:aa|age:12|birthday:2019-10-12 12:02:34|" +
            "dept.deptNo:20|dept.deptName:教学|" +
            "dept.company.cno:1|dept.company.cname:嗷嗷";

    public static void main(String[] args) {
        Employee employee = ObjectInstanceFactory.create(Employee.class, ECHO_DATA);
        System.out.println(employee);
        /*Object o1 = new Employee();
        o1 = new Dept();
        System.out.println(o1);*/
    }
}
