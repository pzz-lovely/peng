package com.peng.abstractfactory.domain.castle;

/**
 * @author lovely
 * @create 2021-03-01 17:26
 * @description
 */
public class ElfCastle implements Castle {
    public static final String DESCRIPTION = "This is the Orc king!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}