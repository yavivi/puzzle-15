package com.puzzle15.command.impl;

import com.puzzle15.board.Puzzle15Board;
import com.puzzle15.command.api.Command;
import com.puzzle15.command.api.Result;

/**
 * Created by yavivi on 24/03/2018.
 */
public class ShuffleBoard extends Command<Integer>{

    public ShuffleBoard(Puzzle15Board board, Integer commandInput) {
        super(board, commandInput);
    }

    @Override
    public Result execute() {
        this.board.shuffle(this.commandInput);
        return new Result(true, "Board Reshuffled");
    }
}
