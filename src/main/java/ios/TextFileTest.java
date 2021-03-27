package ios;

import one.entity.Employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * 文件测试类
 */
public class TextFileTest {
    public static void main(String[] args) {
        Employee[] staff = {new Employee("Carl", 2000, 18),
                new Employee("Harry Hacker", 5000, 30),
                new Employee("Tony Tester", 4000, 45)};
        //save all employee records to the file employee.dat
        try(PrintWriter out = new PrintWriter("employee.dat","UTF-8")) {
            writeData(staff, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try (Scanner in = new Scanner(new FileInputStream("employee.dat"), "utf-8")) {
            Employee[] newStaff = readData(in);
            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * writes all employee in an array to a print writer
     * @param employees
     * @param out
     */
    private static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);
        for (Employee e : employees) {
            writeEmployee(out, e);
        }
    }

    private static void writeEmployee(PrintWriter out, Employee e) {
        out.println(e.getName()+","+e.getMoney()+","+e.getAge());
    }


    private static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    private static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\,");
        return new Employee(tokens[0],Double.parseDouble(tokens[1]),Integer.parseInt(tokens[2]));
    }
}
