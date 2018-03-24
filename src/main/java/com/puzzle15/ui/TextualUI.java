package com.puzzle15.ui;

import com.puzzle15.board.Consts;
import com.puzzle15.board.Puzzle15Board;
import com.puzzle15.command.api.Command;
import com.puzzle15.command.api.CommandFactory;
import com.puzzle15.command.api.Result;
import com.puzzle15.command.impl.ByeCommand;

import java.util.Scanner;

/**
 * Created by yavivi on 24/03/2018.
 */
public class TextualUI implements UI {

    private Puzzle15Board board;

    public TextualUI(Puzzle15Board board){
        this.board = board;
    }

    public void displayGameBoard(String message) {
        System.out.print(Consts.CLEAR_SCREEN);
        System.out.flush();
        System.out.println(board);
        System.out.println(message);
        System.out.println("Number of misplaced tiles : " + board.numOfMisplacedTiles());
    }

    private void displayHelp(){
        System.out.println("Possible commands:");
        System.out.println("1. <tile-to-move> - Type the tile number you wish to move (e.g. 5)");
        System.out.println("2. shuffle <n>    - Shuffle the board by running n random moves");
        System.out.println("3. bye            - leave the game");
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        CommandFactory commandFactory = new CommandFactory();
        displayGameBoard("Let's starts...");
        displayHelp();

        String cmd = "";
        Command userCommand = null;
        while (! (userCommand instanceof ByeCommand)) {
            cmd = scanner.nextLine();                                   //Read user command from stdin
            userCommand = commandFactory.createCommand(board, cmd);     //Convert to executable command
            Result result = userCommand.execute();
            displayGameBoard(result.getMessage());
        }

        scanner.close();
    }

}
