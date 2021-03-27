package com.peng.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lovely
 * @create 2021-03-08 17:38
 * @description
 */
public class IvoryTower implements WizardTower {
    private static final Logger LOGGER = LoggerFactory.getLogger(IvoryTower.class);


    @Override
    public void enter(Wizard wizard) {
        LOGGER.info("{} enters the tower.", wizard);
    }
}