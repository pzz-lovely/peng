package one.chapter9.priorityQueue;

/**
 * @Auther lovely
 * @Create 2020-02-20 11:35
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Man implements Comparable<Man> {
    private String name;
    private int age;

    public Man(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Man man) {
        int diff =Integer.compare(age,man.getAge());
        System.out.println(diff);
        return diff;
    }
}
