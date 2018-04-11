package com.puzzle15.board;

import java.util.*;

public class Puzzle15Board {

    private NewTile emptyTile = null;

    private NewTile[][] board = null;
    private int misplacedTiles = 0;
    private Map<String, NewTile> tilesMap = new HashMap();

    private int dim = 4;

    /**
     * New constructor
     * @param dim
     */
    public Puzzle15Board(int dim){
        initBoard(dim);
    }

    private void initBoard(int dim) {
        this.dim = dim;
        board = new NewTile[dim][dim];

        //Init new board
        int v = 1;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                board[i][j] = new NewTile(dim, v);
                tilesMap.put(""+v, board[i][j]);
                v++;
            }
        }
        this.emptyTile = board[dim-1][dim-1];
    }

    public NewTile getTile(String tileNumber){
        return this.tilesMap.get(tileNumber);
    }

    public void shuffle(int shuffles){
	    Random rand = new Random();
	    NewTile lastMove = null;
        for (int i=0; i<shuffles; i++){
            List<NewTile> movableTiles = calculateMovableTiles();             //returns a list of the tiles that are able to move
            int movableTilesCount = movableTiles.size();
            int randomMove = rand.nextInt(100) % movableTilesCount; //Pick a random tile to move
            NewTile tileToMove = movableTiles.get(randomMove);
            if (tileToMove == lastMove){
                //Dont repeat the last move
                lastMove = movableTiles.stream().filter(t -> t != tileToMove).findAny().get();
            } else {
                lastMove = tileToMove;
            }
            swapTiles(emptyTile, lastMove);           //Swap between the empty tile and the chosen tile
        }
    }

    public boolean moveTile(NewTile tileToMove) {
        if (canMove(tileToMove)){
            this.swapTiles(tileToMove, emptyTile);
            return true;
        } else {
            return false;
        }
    }

    public boolean isGameOver(){
        return allTilesInPlace();
    }

    public int numOfMisplacedTiles(){
        return getMisplacedTiles();
    }

    public NewTile getTile(int i, int j) {
        return this.board[i][j];
    }

    private List<NewTile> calculateMovableTiles() {
	    List<NewTile> result = new ArrayList<>();
	    if (emptyTile.getCol() < dim-1){
	        result.add(board[emptyTile.getRow()][emptyTile.getCol()+1]);
        }
        if (emptyTile.getCol() > 0){
            result.add(board[emptyTile.getRow()][emptyTile.getCol()-1]);
        }
        if (emptyTile.getRow() < dim-1){
            result.add(board[emptyTile.getRow()+1][emptyTile.getCol()]);
        }
        if (emptyTile.getRow() > 0){
            result.add(board[emptyTile.getRow()-1][emptyTile.getCol()]);
        }
	    return result;
    }

    private boolean canMove(NewTile tileToMove) {
        if (tileToMove == emptyTile) {return false;}

	    int row = tileToMove.getRow();
	    int col = tileToMove.getCol();

	    //Verify that a tile is adjacent to the empty tile and can actually move

	    if (row == emptyTile.getRow()){
	        return col-1 == emptyTile.getCol() || col+1 == emptyTile.getCol();
        }
        if (col == emptyTile.getCol()){
            return row-1 == emptyTile.getRow() || row+1 == emptyTile.getRow();
        }

	    return false;
    }

    private void swapTiles(NewTile t1, NewTile t2){
        this.misplacedTiles += t1.swapWith(t2);
        board[t1.getRow()][t1.getCol()] = t1;
        board[t2.getRow()][t2.getCol()] = t2;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                sb.append(board[i][j] == emptyTile ? " __" :  (board[i][j].getValue() > 9 ? " "+board[i][j].getValue() : "  "+board[i][j].getValue()) );
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void reset() {
        this.initBoard(dim);
    }

    public boolean allTilesInPlace(){
        return misplacedTiles == 0;
    }
    public int getMisplacedTiles(){
        return misplacedTiles;
    }

    public NewTile getEmptyTile() {
        return emptyTile;
    }
}
