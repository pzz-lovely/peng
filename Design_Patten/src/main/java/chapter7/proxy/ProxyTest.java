package chapter7.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        SchoolGirl jiao = new SchoolGirl("½¿½¿");
        Proxy proxy = new Proxy(jiao);
        proxy.GiveChocolate();
        proxy.GiveDolls();
        proxy.GiveFlowers();
    }
}
