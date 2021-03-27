package com.peng.abstractfactory.factory;

import com.peng.abstractfactory.domain.army.Army;
import com.peng.abstractfactory.domain.army.ElfArmy;
import com.peng.abstractfactory.domain.castle.Castle;
import com.peng.abstractfactory.domain.castle.ElfCastle;
import com.peng.abstractfactory.domain.king.ElfKing;
import com.peng.abstractfactory.domain.king.King;

/**
 * @author lovely
 * @create 2021-03-01 17:31
 * @description
 */
public class ElfKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }

    @Override
    public King createKing() {
        return new ElfKing();
    }

    @Override
    public Army createArmy() {
        return new ElfArmy();
    }
}