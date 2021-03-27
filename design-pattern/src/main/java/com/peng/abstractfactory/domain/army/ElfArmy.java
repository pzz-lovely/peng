package com.peng.abstractfactory.domain.army;

/**
 * @author lovely
 * @create 2021-03-01 17:18
 * @description
 */
public class ElfArmy implements Army {
    static final String DESCRIPTION = "This is the Elven Army!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}