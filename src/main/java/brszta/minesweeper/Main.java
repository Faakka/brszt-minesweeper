package brszta.minesweeper;

import brszta.minesweeper.backend.game.Board;
import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.backend.game.Highscores;
import brszta.minesweeper.backend.io.JsonIO;
import brszta.minesweeper.backend.game.Score;
import brszta.minesweeper.backend.test.TestAction;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        game.setType("single");
        game.setLevel(1);
        game.setBoard(new Board(1));
        game.getBoard().generate();
        game.setStartTime();

        Highscores hs = new Highscores();
        JsonIO jsonIO = new JsonIO();
        jsonIO.readScores(hs.getList(1), 1);
        hs.formattedPrint(1);

        TestAction.drawBoard(game.getBoard());

        boolean isOver = false;
        int response = 0;

        while(!isOver) {
            TestAction.printStat(game.getBoard());
            response = TestAction.pickTile(game.getBoard());
            if(response == 1 || response == 2)
                isOver = true;
            TestAction.drawBoard(game.getBoard());
        }

        int gameTimeInSec = game.calcGameTime();

        if(response == 1)
            System.out.println("\nGAME OVER");
        else {
            Score newScore = new Score("boti", game.getLevel(), gameTimeInSec);
            System.out.println("\nYOU WON (" + newScore.getFormattedTime() + ")");
            hs.appendScore(newScore);
            hs.sortScores(game.getLevel());
            jsonIO.writeScores(hs.getList(1), 1);
        }

    }

}
