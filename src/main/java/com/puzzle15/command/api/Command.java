package com.puzzle15.command.api;

import com.puzzle15.board.Puzzle15Board;

public abstract class Command<T> {

	protected Puzzle15Board board;
	protected String name;
	protected T commandInput;
	
	public Command(Puzzle15Board board, T commandInput) {
		this.board = board;
		this.commandInput = commandInput;
	}
	
	public abstract Result execute();
}
