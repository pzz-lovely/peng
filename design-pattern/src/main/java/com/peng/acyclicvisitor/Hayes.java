package com.peng.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lovely
 * @create 2021-03-02 16:25
 * @description
 */
public class Hayes extends Modem {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hayes.class);

    @Override
    public void accept(ModemVisitor modemVisitor) {
        if (modemVisitor instanceof HayesVisitor) {
            ((HayesVisitor) modemVisitor).visit(this);
        }
        LOGGER.info("Only HayesVisitor is allowed to visit Hayes modem");
    }

    @Override
    public String toString() {
        return "Hayes modem";
    }
}