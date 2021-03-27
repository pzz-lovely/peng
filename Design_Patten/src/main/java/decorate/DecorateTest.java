package decorate;

/**
 * @Auther lovely
 * @Create 2020-02-18 20:26
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class DecorateTest {
    public static void main(String[] args) {
        Command command = new LoggerDecorate(new PerformanceDecorate(new PlaceOrderCommand()));
        command.execute();
    }
}
