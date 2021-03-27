package com.peng.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lovely
 * @create 2021-03-02 16:31
 * @description
 */
public class ConfigureForUnixVisitor implements ZoomVisitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigureForUnixVisitor.class);


    @Override
    public void visitor(Zoom zoom) {
LOGGER.info(zoom + "used with Unix configurator.");
    }
}