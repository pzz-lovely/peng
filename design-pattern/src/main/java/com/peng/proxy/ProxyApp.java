package com.peng.proxy;

/**
 * @author lovely
 * @create 2021-03-08 17:36
 * @description
 */
public class ProxyApp {
    public static void main(String[] args) {
        var proxy = new WizardTowerProxy(new IvoryTower());
        proxy.enter(new Wizard("Red wizard"));
        proxy.enter(new Wizard("White wizard"));
        proxy.enter(new Wizard("Black wizard"));
        proxy.enter(new Wizard("Green wizard"));
        proxy.enter(new Wizard("Brown wizard"));
    }
}