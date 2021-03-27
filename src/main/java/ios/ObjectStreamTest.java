package ios;

import one.entity.Employee;

import java.io.*;

public class ObjectStreamTest implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee employee = new Employee("0.0", 100, 10);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("employee.txt"));
        objectOutputStream.writeObject(employee);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("employee.txt"));
        Employee e = (Employee) objectInputStream.readObject();
        System.out.println(e);
        objectOutputStream.close();
    }
}
