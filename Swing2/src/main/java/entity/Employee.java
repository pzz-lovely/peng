package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther lovely
 * @Create 2020-03-13 10:07
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private String name;
    public static final int NAME_SIZE = 5;
    private double salary;
    private int year;
    private int month;
    private int day;
}
