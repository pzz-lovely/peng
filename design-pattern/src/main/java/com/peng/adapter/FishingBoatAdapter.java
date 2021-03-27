package com.peng.adapter;

/**
 * @author lovely
 * @create 2021-03-02 16:49
 * @description
 */
public class FishingBoatAdapter extends RowingBoat {
    private final FishingBoat boat;

    public FishingBoatAdapter() {
        boat = new FishingBoat();
    }

    public final void row(){
        boat.sail();
    }
}