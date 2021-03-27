package one.chapter9.map;

import one.entity.Employee;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther lovely
 * @Create 2020-02-20 11:45
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Employee> staff = new HashMap<>();
        staff.put("144-25-5464", new Employee("Amy Lee", 1000, new Date()));
        staff.put("567-24-2546", new Employee("Harry Hacker", 500, new Date()));
        staff.put("157-62-7935", new Employee("Gary Gooper", 500, new Date()));
        staff.put("456-62-5527", new Employee("FRANCESCA Cruz", 500, new Date()));

        System.out.println(staff);

        staff.remove("157-62-7935");

        staff.forEach((key,value) ->{
            System.out.println("key = " + key + " value = " + value);
        });
    }
}
