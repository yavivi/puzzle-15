package com.puzzle15.board;

import java.util.Arrays;

/**
 * Created by yavivi on 24/03/2018.
 */
public enum OldTile {
    TILE_1(1),
    TILE_2(2),
    TILE_3(3),
    TILE_4(4),
    TILE_5(5),
    TILE_6(6),
    TILE_7(7),
    TILE_8(8),
    TILE_9(9),
    TILE_10(10),
    TILE_11(11),
    TILE_12(12),
    TILE_13(13),
    TILE_14(14),
    TILE_15(15),
    TILE_16(16); //The empty Tile

    private static int misplacedTiles = 0;

    static boolean allTilesInPlace(){
        return misplacedTiles == 0;
    }
    static int getMisplacedTiles(){
        return misplacedTiles;
    }

    static void resetAll(){
        Arrays.asList(OldTile.values()).stream().forEach(t->t.resetLocation());
        misplacedTiles=0;
    }

    private final int tileNumber;
    private int row = 0;
    private int col = 0;

    OldTile(int n) {
        this.tileNumber = n;
        resetLocation();
    }

    void resetLocation(){
        row = (tileNumber-1) / 4;
        col = (tileNumber-1) % 4;
    }

    void swapWith(OldTile t){
        int tmpR = row;
        int tmpC = col;
        this.setLocation(t.getRow(), t.getCol());
        t.setLocation(tmpR, tmpC);
    }

    void setLocation(int row, int col){
        boolean wasInPlace = !isMisplaced();
        this.row = row;
        this.col = col;
        boolean misplaced = isMisplaced();
        if (!misplaced){
            //tile is back to its place
            misplacedTiles--;
        } else if (wasInPlace){
            //tile was just moved from its place
            misplacedTiles++;
        }
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public int getValue(){
        return tileNumber;
    }

    boolean isMisplaced(){
        return row != (tileNumber-1) / 4 || col != (tileNumber-1) % 4;
    }

    public String getDisplayValue() {
        return tileNumber == 16 ? "" : ""+tileNumber;
    }
}
