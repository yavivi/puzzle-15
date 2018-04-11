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

        if (args.length == 0) {
            Puzzle15Board board = new Puzzle15Board(4);
            board.shuffle(Consts.DEFAULT_SHUFFLES);
            ui = new GraphicalUI(board);
        } else {

            try {
                String uiType = args[0];
                int dim = Integer.parseInt(args[1]);
                Puzzle15Board board = new Puzzle15Board(dim);

                if ("gui".equals(uiType)) {
                    board.shuffle(Consts.DEFAULT_SHUFFLES);
                    ui = new GraphicalUI(board);
                } else if ("text".equals(uiType)) {
                    ui = new TextualUI(board);
                }
            } catch (Exception e) {
                usage();
            }

        }

        if (ui != null) {
            ui.start();
        } else {
            usage();
        }
    }

    private static void usage(){
        System.out.println("Usage: [text|gui] [dimension]");
    }

}
