package chapter8.factory;

import chapter8.LeiFeng;
import chapter8.Volunteer;

public class VolunteerFactory implements IFactory {
    @Override
    public LeiFeng createLeiFeng() {
        return new Volunteer();
    }
}
