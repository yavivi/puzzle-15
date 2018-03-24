package com.puzzle15.command.impl;

import com.puzzle15.board.Puzzle15Board;
import com.puzzle15.command.api.Command;
import com.puzzle15.command.api.Result;

public class ByeCommand extends Command<String> {

	public ByeCommand(Puzzle15Board board, String commandInput) {
		super(board, commandInput);
	}

	@Override
	public Result execute() {
		return new Result(true, "See ya" );
	}

}
