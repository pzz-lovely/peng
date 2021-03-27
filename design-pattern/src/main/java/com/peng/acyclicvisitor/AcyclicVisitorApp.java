package com.peng.acyclicvisitor;

/**
 * @author lovely
 * @create 2021-03-02 16:16
 * @description 非循环访问者模式
 */
public class AcyclicVisitorApp {
    public static void main(String[] args) {
        var conUnix = new ConfigureForUnixVisitor();
        var conDos = new ConfigureForDosVisitor();

        var zoom = new Zoom();
        var hayes = new Zoom();

        hayes.accept(conDos);
        zoom.accept(conDos);
        hayes.accept(conUnix);
        zoom.accept(conUnix);
    }
}