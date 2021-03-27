package com.peng.acyclicvisitor;

/**
 * @author lovely
 * @create 2021-03-02 16:20
 * @description
 */
public abstract class Modem {
    public abstract void accept(ModemVisitor modemVisitor);
}