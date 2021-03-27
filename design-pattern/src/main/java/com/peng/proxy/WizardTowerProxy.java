package com.peng.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author lovely
 * @create 2021-03-08 17:40
 * @description
 */
public class WizardTowerProxy implements WizardTower {
    private static final Logger LOGGER = LoggerFactory.getLogger(WizardTowerProxy.class);

    private static final int NUM_WIZARDS_ALLOWED = 3;

    private int numWizards;

    private final WizardTower tower;

    public WizardTowerProxy(WizardTower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < NUM_WIZARDS_ALLOWED) {
            tower.enter(wizard);
            numWizards++;
        } else {
            LOGGER.info("{} is not allowed to enter!", wizard);
        }
    }
}
