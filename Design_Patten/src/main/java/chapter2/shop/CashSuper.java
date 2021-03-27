package chapter2.shop;

/**
 * 现金收费类
 */
public abstract class CashSuper {
    /**
     * 子类实现此方法 计算出 商场里面 正常收费 打折收费 返利收费
     * @param money
     * @return
     */
    public abstract double acceptCash(double money);
}
