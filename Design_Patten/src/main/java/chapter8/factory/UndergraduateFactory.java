package chapter8.factory;

import chapter8.LeiFeng;
import chapter8.Undergraduate;

public class UndergraduateFactory implements IFactory {
    @Override
    public LeiFeng createLeiFeng() {
        return new Undergraduate();
    }
}
