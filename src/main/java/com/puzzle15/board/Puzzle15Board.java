package com.puzzle15.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.puzzle15.board.Consts.SIZE;
import static com.puzzle15.board.Tile.*;

public class Puzzle15Board {

    private final static Tile EMPTY_TILE = Tile.TILE_16;

    private Tile[][] board = {
            { TILE_1, TILE_2, TILE_3, TILE_4 },
            { TILE_5, TILE_6, TILE_7, TILE_8 },
            { TILE_9, TILE_10, TILE_11, TILE_12 },
            { TILE_13, TILE_14, TILE_15, EMPTY_TILE },
    };

	public void shuffle(int shuffles){
	    Random rand = new Random();
	    Tile lastMove = null;
        for (int i=0; i<shuffles; i++){
            List<Tile> movableTiles = calculateMovableTiles();             //returns a list of the tiles that are able to move
            int movableTilesCount = movableTiles.size();
            int randomMove = rand.nextInt(100) % movableTilesCount; //Pick a random tile to move
            Tile tileToMove = movableTiles.get(randomMove);
            if (tileToMove == lastMove){
                //Dont repeat the last move
                lastMove = movableTiles.stream().filter(t -> t != tileToMove).findAny().get();
            } else {
                lastMove = tileToMove;
            }
            swapTiles(EMPTY_TILE, lastMove);           //Swap between the empty tile and the chosen tile
        }
    }

    public boolean moveTile(Tile tileToMove) {
        if (canMove(tileToMove)){
            this.swapTiles(tileToMove, EMPTY_TILE);
            return true;
        } else {
            return false;
        }
    }

    public boolean isGameOver(){
        return Tile.allTilesInPlace();
    }
    public int numOfMisplacedTiles(){
        return Tile.getMisplacedTiles();
    }
    public Tile getTile(int i, int j) {
        return this.board[i][j];
    }

    private List<Tile> calculateMovableTiles() {
	    List<Tile> result = new ArrayList<>();
	    if (EMPTY_TILE.getCol() < SIZE-1){
	        result.add(board[EMPTY_TILE.getRow()][EMPTY_TILE.getCol()+1]);
        }
        if (EMPTY_TILE.getCol() > 0){
            result.add(board[EMPTY_TILE.getRow()][EMPTY_TILE.getCol()-1]);
        }
        if (EMPTY_TILE.getRow() < SIZE-1){
            result.add(board[EMPTY_TILE.getRow()+1][EMPTY_TILE.getCol()]);
        }
        if (EMPTY_TILE.getRow() > 0){
            result.add(board[EMPTY_TILE.getRow()-1][EMPTY_TILE.getCol()]);
        }
	    return result;
    }

    private boolean canMove(Tile tileToMove) {
        if (tileToMove == EMPTY_TILE) {return false;}

	    int row = tileToMove.getRow();
	    int col = tileToMove.getCol();

	    //Verify that a tile is adjacent to the empty tile and can actually move

	    if (row == EMPTY_TILE.getRow()){
	        return col-1 == EMPTY_TILE.getCol() || col+1 == EMPTY_TILE.getCol();
        }
        if (col == EMPTY_TILE.getCol()){
            return row-1 == EMPTY_TILE.getRow() || row+1 == EMPTY_TILE.getRow();
        }

	    return false;
    }

    private void swapTiles(Tile t1, Tile t2){
        t1.swapWith(t2);
        board[t1.getRow()][t1.getCol()] = t1;
        board[t2.getRow()][t2.getCol()] = t2;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(board[i][j] == Tile.TILE_16 ? " __" :  (board[i][j].getValue() > 9 ? " "+board[i][j].getValue() : "  "+board[i][j].getValue()) );
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void reset() {
        Tile.resetAll();
    }
}
