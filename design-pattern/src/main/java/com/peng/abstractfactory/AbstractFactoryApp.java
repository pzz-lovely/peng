package com.peng.abstractfactory;

import com.peng.abstractfactory.factory.KingdomFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author lovely
 * @create 2021-03-01 17:01
 * @description
 */
public class AbstractFactoryApp implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(AbstractFactoryApp.class);

    private final Kingdom kingdom = new Kingdom();

    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        var app = new AbstractFactoryApp();
        app.run();
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    @Override
    public void run() {
        log.info("Elf Kingdom");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
        log.info(kingdom.getArmy().getDescription());
        log.info(kingdom.getCastle().getDescription());
        log.info(kingdom.getKing().getDescription());

        log.info("Orc Kingdom");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
        log.info(kingdom.getArmy().getDescription());
        log.info(kingdom.getCastle().getDescription());
        log.info(kingdom.getKing().getDescription());
    }

    /**
     * Creates kingdom.
     *
     * @param kingdomType type of Kingdom
     */
    public void createKingdom(final Kingdom.FactoryMaker.KingdomType kingdomType) {
        final KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(kingdomType);
        kingdom.setKing(kingdomFactory.createKing());
        kingdom.setCastle(kingdomFactory.createCastle());
        kingdom.setArmy(kingdomFactory.createArmy());
    }
}