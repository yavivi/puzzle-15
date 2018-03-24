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
        Puzzle15Board board = new Puzzle15Board();
        UI ui = null;

        if (args.length == 1 && "gui".equals(args[0])){
            //Load the graphical UI. Shuffle the board 50 times by default (@ TODO add a shuffle button to GUI)
            board.shuffle(Consts.GUI_DEFAULT_SHUFFLES);
            ui = new GraphicalUI(board);
        } else {
            //Default UI is a Textual UI
            ui = new TextualUI(board);
        }

        ui.start();
    }
}
