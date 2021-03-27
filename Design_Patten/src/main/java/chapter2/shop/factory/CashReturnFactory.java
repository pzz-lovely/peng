package chapter2.shop.factory;

import chapter2.shop.CashNormal;
import chapter2.shop.CashRebate;
import chapter2.shop.CashReturn;
import chapter2.shop.CashSuper;

public class CashReturnFactory {
    public static CashSuper createCashReturn(String type){
        CashSuper cashReturn = null;
        switch (type) {
            case "�����շ�":
                cashReturn = new CashNormal();
                break;
            case "��300��100":
                cashReturn = new CashReturn(300,100);
                break;
            case "��8��":
                cashReturn = new CashRebate(0.8);
                break;
        }
        return cashReturn;
    }
}
