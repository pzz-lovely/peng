package chapter7.proxy;

public class Pursuit implements IGiveGift {
    SchoolGirl mm;


    public Pursuit(SchoolGirl girl) {
        this.mm = girl;
    }

    @Override
    public void GiveDolls() {
        System.out.println(mm.getName()+"ËÍÄãÑóÍŞÍŞ");
    }

    @Override
    public void GiveFlowers() {
        System.out.println(mm.getName()+"ËÍÄãÏÊ»¨");
    }

    @Override
    public void GiveChocolate() {
        System.out.println(mm.getName()+"ËÍÄãÇÉ¿ËÁ¦");
    }
}
