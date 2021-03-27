package one.chapter6.decorator2;

public class Clothes {
    private Finery finery;

    public Clothes(Finery finery) {
        this.finery = finery;
    }

    public String clothes(){
        return "¥©…œ¡À"+finery.getName();
    }
}
