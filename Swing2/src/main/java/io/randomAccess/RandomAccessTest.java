package io.randomAccess;

import entity.Employee;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * @Auther lovely
 * @Create 2020-03-13 10:09
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class RandomAccessTest {
    public static void main(String[] args) throws IOException {
        Employee e[] = new Employee[]{
                new Employee("0.0", 500, 2020, 3, 13),
                new Employee("0.1", 200, 2019, 4, 5),
                new Employee("0.2", 700, 2013, 7, 12)
        };

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("Employee.dat"))) {
            for(Employee employee : e)
                writeData(out,employee);
        }

        try (RandomAccessFile in = new RandomAccessFile("Employee.dat", "r")) {
            int n = (int) (in.length() / 2);
            Employee[] newStaff = new Employee[n];
            for (int i = n - 1; i >= 0; i++) {
                newStaff[i] = new Employee();
                in.seek(i * 2);
                newStaff[i] = readDate(in);
            }

            System.out.println(Arrays.toString(newStaff));

        }
    }

    /**
     * writes employee data to a data output
     * @param output
     * @param e
     * @throws IOException
     */
    public static void writeData(DataOutput output, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), e.NAME_SIZE, output);
        output.writeDouble(e.getSalary());
        LocalDate hireDay = LocalDate.of(e.getYear(), e.getMonth(), e.getDay());
        output.writeInt(hireDay.getYear());
        output.writeInt(hireDay.getMonthValue());
        output.writeInt(hireDay.getDayOfMonth());
    }


    public static Employee readDate(DataInput in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m - 1, d);
    }
}
