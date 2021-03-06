package com.puzzle15.test;
/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import com.puzzle15.board.Tile;
import com.puzzle15.board.Puzzle15Board;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Puzzle15BoardTest {
    private Puzzle15Board board = new Puzzle15Board(4);

    @Before
    public void setup(){
        board.reset();
    }

	@Test 
    public void testCreateNewBoard() {
	    assertThat(board.numOfMisplacedTiles()).isEqualTo(0);
    }

    @Test
    public void testMoveTile(){
        Tile EMPTY_TILE = board.getEmptyTile();
        board.moveTile(board.getTile("15"));
        assertThat(board.getTile(3, 3)).isEqualTo(board.getTile("15"));
        assertThat(board.getTile(3, 2)).isEqualTo(EMPTY_TILE);
        board.moveTile(board.getTile("11"));
        assertThat(board.getTile(2, 2)).isEqualTo(EMPTY_TILE);
        assertThat(board.getTile(3, 2)).isEqualTo(board.getTile("11"));

        assertThat(board.numOfMisplacedTiles()).isEqualTo(3);
    }

    @Test
    public void testTryToMoveEmptyTile(){
        Tile EMPTY_TILE = board.getEmptyTile();
        boolean success = board.moveTile(EMPTY_TILE);
        assertThat(success).isEqualTo(false);
    }

    @Test
    public void testShuffle1(){
        board.shuffle(1);
        assertThat(board.numOfMisplacedTiles()).isEqualTo(2);  //1 tile + the empty tile
    }

    @Test
    public void testShuffle2(){
        board.shuffle(2);
        assertThat(board.numOfMisplacedTiles()).isEqualTo(3);  //2 tiles + the empty tile
    }
}
