package com.puzzle15.bootstrap;

import com.puzzle15.board.*;
import com.puzzle15.ui.GraphicalUI;
import com.puzzle15.ui.TextualUI;
import com.puzzle15.ui.UI;

/**
 * Created by yavivi on 23/03/2018.
 */
public class GameStarter {

    public static void main(String[] args) {
        UI ui = null;

        if (args.length == 0){
            Puzzle15Board board = new Puzzle15Board(4);
            //Load the graphical UI. Shuffle the board 50 times by default (@ TODO add a shuffle button to GUI)
            board.shuffle(Consts.DEFAULT_SHUFFLES);
            ui = new GraphicalUI(board);
        } else {

            try {
                String uiType = args[0];
                int dim = Integer.parseInt(args[1]);
                Puzzle15Board board = new Puzzle15Board(dim);
                switch (uiType){
                    case "gui":
                        ui = new GraphicalUI(board);
                        break;
                    case "text":
                        ui = new TextualUI(board);
                        break;
                }
                ui = new TextualUI(board);
            } catch (Exception e) {
                System.out.println("Usage: [text|gui] [dimension]");
            }

        }

        ui.start();
    }
}
