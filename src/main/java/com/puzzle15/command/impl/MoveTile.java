package com.puzzle15.command.impl;

import com.puzzle15.board.Consts;
import com.puzzle15.board.NewTile;
import com.puzzle15.board.Puzzle15Board;
import com.puzzle15.board.Tile;
import com.puzzle15.command.api.Command;
import com.puzzle15.command.api.Result;

/**
 * Created by yavivi on 23/03/2018.
 */
public class MoveTile extends Command<String> {

    public MoveTile(Puzzle15Board board, String tile) {
        super(board, tile);
        this.commandInput = tile;
    }

    @Override
    public Result execute() {
        NewTile tileToMove = null;
        try {

            tileToMove = board.getTile(this.commandInput);

        } catch (IllegalArgumentException e) {
            return new Result(false, "No such tile or invalid command |" + commandInput + "|");
        }

        boolean success = this.board.moveTile(tileToMove);
        String message = "tile "+ commandInput + " moved.";
        if (success) {
            if (board.isGameOver()){
                message = "Well done! All tiles in place!";
            }
            return new Result(true, message);
        }
        return new Result(false, "Cannot move this tile");
    }
}
