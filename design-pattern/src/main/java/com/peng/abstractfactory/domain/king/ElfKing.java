package com.peng.abstractfactory.domain.king;

/**
 * @author lovely
 * @create 2021-03-01 17:10
 * @description 精灵王
 */
public class ElfKing implements King {
    public static final String DESCRIPTION = "This is the Elven king!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}