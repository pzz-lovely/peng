package com.peng.abstractfactory.domain.castle;

/**
 * @author lovely
 * @create 2021-03-01 17:25
 * @description
 */
public class OrcCastle implements Castle {
    public static final String DESCRIPTION = "This is the Orc castle!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}