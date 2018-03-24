package com.puzzle15.command.api;

import com.puzzle15.board.Puzzle15Board;
import com.puzzle15.command.impl.ByeCommand;
import com.puzzle15.command.impl.MoveTile;
import com.puzzle15.command.impl.ShuffleBoard;

import java.util.Arrays;
import java.util.List;

public class CommandFactory {

    public Command<?> createCommand(Puzzle15Board board, String commandLine) {
        Command<?> result = null;

        //Split command line on white spaces of any size
        List<String> commandArray = Arrays.asList(commandLine.trim().split("\\s+"));

        String commandName = commandArray.get(0);

        switch (commandName) {
            case "shuffle":
                result = new ShuffleBoard(board, Integer.parseInt(commandArray.get(1)));
                break;
            case "bye":
                result = new ByeCommand(board, "Bye");
                break;
            default:
                result = new MoveTile(board, commandName);
                break;
        }

        return result;
    }
}
