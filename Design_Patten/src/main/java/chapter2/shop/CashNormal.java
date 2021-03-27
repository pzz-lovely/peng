package chapter2.shop;

/**
 * 正常收费
 */
public class CashNormal extends CashSuper {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
