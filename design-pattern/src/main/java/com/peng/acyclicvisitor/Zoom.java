package com.peng.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lovely
 * @create 2021-03-02 16:19
 * @description
 */
public class Zoom extends Modem {
    private static final Logger LOGGER = LoggerFactory.getLogger(Zoom.class);


    @Override
    public void accept(ModemVisitor modemVisitor) {
        if (modemVisitor instanceof ZoomVisitor) {
            ((ZoomVisitor) modemVisitor).visitor(this);
        }else{
            LOGGER.info("Only ZoomVisitor is allowed to visit Zoom modem.");
        }
    }

    @Override
    public String toString() {
        return "Zoom modem";
    }
}