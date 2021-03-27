package chapter2.shop;

/**
 * 返利收费 比如满 300 退 100
 */
public class CashReturn extends CashSuper {
    private double moneyCondition = 300;
    private double moneyReturn = 100;

    public CashReturn(double moneyCondition, double moneyReturn) {
        this.moneyReturn = moneyReturn;
        this.moneyCondition = moneyCondition;
    }
    @Override
    public double acceptCash(double money) {
        if (money > moneyCondition) {
            return money - moneyReturn;
        }
        return money;
    }
}
