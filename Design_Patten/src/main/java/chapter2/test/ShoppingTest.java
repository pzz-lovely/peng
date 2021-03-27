package chapter2.test;

import chapter2.shop.CashReturn;
import chapter2.shop.CashSuper;
import chapter2.shop.factory.CashReturnFactory;

public class ShoppingTest {
    public static void main(String[] args) {
        CashSuper cashSuper = CashReturnFactory.createCashReturn("Дђ8ел");
        System.out.println(cashSuper.acceptCash(1000));;
    }
}
