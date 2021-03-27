package reflex;

public class Person {
    private String name;
    private String description;
    private int age;
    private Person(String name) {
        this.name = name;
    }

    public Person(String name, String description, int age) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    Person(int age, String description) {
        this.age = age;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                '}';
    }

    private void sayName(String name) {
        System.out.println("ÐÕÃû : "+name);
    }
    public int getAge(){
        return age;
    }

    protected double getCost(double num1,double num2){
        return num1 + num2;
    }
}
