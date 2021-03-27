package com.peng.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lovely
 * @create 2021-03-02 16:46
 * @description
 */
public class FishingBoat {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishingBoat.class);

    void sail() {
        LOGGER.info("The fishings boat is sailing");
    }
}