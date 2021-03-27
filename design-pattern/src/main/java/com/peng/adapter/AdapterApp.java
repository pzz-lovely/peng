package com.peng.adapter;

/**
 * @author lovely
 * @create 2021-03-02 16:43
 * @description
 */
public class AdapterApp {
    public static void main(String[] args) {
        var captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}