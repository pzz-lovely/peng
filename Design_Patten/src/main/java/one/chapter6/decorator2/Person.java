package one.chapter6.decorator2;

public class Person {
    public Person(){}

    private Clothes clothes;

    private String name;

    private StringBuilder sb = new StringBuilder();


    public Person(String name) {
        this.name = name;
    }

    public void setClothes(Finery finery) {
        clothes = new Clothes(finery);
        sb.append(clothes.clothes());
    }

    public void show(){
        System.out.printf("%s%s",name,sb.toString());
    }



}
