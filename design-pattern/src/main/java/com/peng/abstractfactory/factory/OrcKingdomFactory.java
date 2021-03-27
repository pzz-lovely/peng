package com.peng.abstractfactory.factory;

import com.peng.abstractfactory.domain.army.Army;
import com.peng.abstractfactory.domain.army.OrcArmy;
import com.peng.abstractfactory.domain.castle.Castle;
import com.peng.abstractfactory.domain.castle.OrcCastle;
import com.peng.abstractfactory.domain.king.King;
import com.peng.abstractfactory.domain.king.OrcKing;

/**
 * @author lovely
 * @create 2021-03-01 17:32
 * @description
 */
public class OrcKingdomFactory implements KingdomFactory {

    @Override
    public Castle createCastle() {
        return new OrcCastle();
    }

    @Override
    public King createKing() {
        return new OrcKing();
    }

    @Override
    public Army createArmy() {
        return new OrcArmy();
    }
}