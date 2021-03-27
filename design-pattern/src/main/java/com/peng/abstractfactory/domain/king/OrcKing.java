package com.peng.abstractfactory.domain.king;

/**
 * @author lovely
 * @create 2021-03-01 17:08
 * @description 兽人王
 */
public class OrcKing implements King {

    public static final String DESCRIPTION = "This is the Orc King!";


    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}