package com.peng.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lovely
 * @create 2021-03-02 16:29
 * @description
 */
public class ConfigureForDosVisitor implements AllModemVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigureForDosVisitor.class);

    @Override
    public void visit(Hayes hayes) {
        LOGGER.info(hayes + " used with dos configurator.");
    }

    @Override
    public void visitor(Zoom zoom) {
        LOGGER.info(zoom + " used with dos configurator.");
    }
}