package com.peng.acyclicvisitor;

/**
 * @author lovely
 * @create 2021-03-02 16:24
 * @description
 */
public interface HayesVisitor extends ModemVisitor {
    void visit(Hayes hayes);
}