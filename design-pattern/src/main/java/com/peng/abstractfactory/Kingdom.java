package com.peng.abstractfactory;

import com.peng.abstractfactory.domain.army.Army;
import com.peng.abstractfactory.domain.castle.Castle;
import com.peng.abstractfactory.domain.king.King;
import com.peng.abstractfactory.factory.ElfKingdomFactory;
import com.peng.abstractfactory.factory.KingdomFactory;
import com.peng.abstractfactory.factory.OrcKingdomFactory;

/**
 * @author lovely
 * @create 2021-03-01 17:35
 * @description
 */
public class Kingdom {
    private King king;
    private Castle castle;
    private Army army;

    public King getKing() {
        return king;
    }

    public Castle getCastle() {
        return castle;
    }

    public Army getArmy() {
        return army;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public void setArmy(Army army) {
        this.army = army;
    }


    /**
     * The factory of kingdom factories.
     */
    public static class FactoryMaker {

        /**
         * Enumeration for the different types of Kingdoms.
         */
        public enum KingdomType {
            ELF, ORC
        }

        /**
         * The factory method to create KingdomFactory concrete objects.
         */
        public static KingdomFactory makeFactory(KingdomType type) {
            switch (type) {
                case ELF:
                    return new ElfKingdomFactory();
                case ORC:
                    return new OrcKingdomFactory();
                default:
                    throw new IllegalArgumentException("KingdomType not supported.");
            }
        }
    }
}