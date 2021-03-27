package one.chapter6.decorator2;

public class DecoratorTest {
    public static void main(String[] args) {
        Finery trouser = new BigTrouser("���");
        Finery shirts = new TShirts("T��");
        Person p = new Person("С��");
        p.setClothes(trouser);
        p.setClothes(shirts);
        p.show();
    }
}
