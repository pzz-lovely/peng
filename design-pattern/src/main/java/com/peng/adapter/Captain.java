package com.peng.adapter;

/**
 * @author lovely
 * @create 2021-03-02 16:44
 * @description
 */
public class Captain extends RowingBoat {

    private RowingBoat rowingBoat;

    public Captain() {
    }

    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void setRowingBoat(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    @Override
    void row() {
        rowingBoat.row();
    }
}