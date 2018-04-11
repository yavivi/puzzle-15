package com.puzzle15.board;

/**
 * Created by yavivi on 24/03/2018.
 */
public class Tile {

    private int value;
    private int row = 0;
    private int col = 0;
    private int dim = 0;

    public Tile(int dim, int value){
        this.dim = dim;
        this.value = value;
        resetLocation();
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private void resetLocation(){
        row = (value-1) / dim;
        col = (value-1) % dim;
    }

    public int swapWith(Tile t){
        int tmpR = row;
        int tmpC = col;
        return this.setLocation(t.getRow(), t.getCol()) + t.setLocation(tmpR, tmpC);
    }

    private int setLocation(int row, int col){
        boolean wasInPlace = !isMisplaced();
        this.row = row;
        this.col = col;
        boolean misplaced = isMisplaced();
        if (!misplaced){
            //tile is back to its place
            return -1;
        } else if (wasInPlace){
            //tile was just moved from its place
            return 1;
        }
        return 0;
    }

    public boolean isMisplaced(){
        return row != (value-1) / dim || col != (value-1) % dim;
    }

    public String getDisplayValue() {
        return value == dim*dim ? "" : ""+value;
    }

    @Override
    public boolean equals(Object obj) {
        Tile other = (Tile) obj;

        return this.getValue() == other.getValue();
    }
}
