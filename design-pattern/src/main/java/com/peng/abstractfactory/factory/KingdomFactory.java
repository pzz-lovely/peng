package com.peng.abstractfactory.factory;

import com.peng.abstractfactory.domain.army.Army;
import com.peng.abstractfactory.domain.castle.Castle;
import com.peng.abstractfactory.domain.king.King;

/**
 * @Auther lovely
 * @Create 2021-03-01 17:27
 * @Description todo
 */
public interface KingdomFactory {
    Castle createCastle();

    King createKing();

    Army createArmy();
}
