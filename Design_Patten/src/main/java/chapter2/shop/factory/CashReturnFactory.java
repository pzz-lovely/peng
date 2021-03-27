package chapter2.shop.factory;

import chapter2.shop.CashNormal;
import chapter2.shop.CashRebate;
import chapter2.shop.CashReturn;
import chapter2.shop.CashSuper;

public class CashReturnFactory {
    public static CashSuper createCashReturn(String type){
        CashSuper cashReturn = null;
        switch (type) {
            case "正常收费":
                cashReturn = new CashNormal();
                break;
            case "满300减100":
                cashReturn = new CashReturn(300,100);
                break;
            case "打8折":
                cashReturn = new CashRebate(0.8);
                break;
        }
        return cashReturn;
    }
}
