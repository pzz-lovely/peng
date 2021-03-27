package one.chapter6.decorator2;

public class DecoratorTest {
    public static void main(String[] args) {
        Finery trouser = new BigTrouser("¿å¿ã");
        Finery shirts = new TShirts("TÐô");
        Person p = new Person("Ð¡Ã÷");
        p.setClothes(trouser);
        p.setClothes(shirts);
        p.show();
    }
}
