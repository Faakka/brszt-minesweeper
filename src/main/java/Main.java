import brszta.minesweeper.backend.game.Board;
import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.backend.game.Highscores;
import brszta.minesweeper.backend.io.JsonIO;
import brszta.minesweeper.backend.game.Score;
import brszta.minesweeper.backend.test.TestAction;
import brszta.minesweeper.gui.Click;
import brszta.minesweeper.gui.Display;
import brszta.minesweeper.gui.GUI;
import brszta.minesweeper.gui.Menu;

public class Main {

    public static void main(String[] args) {
/*
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
        }*/

        Game game = new Game();
        game.setType("single");
        game.setLevel(1);
        game.setBoard(new Board(1));
        game.getBoard().generate();

        Click click = new Click();
        Display display = new Display(game.getBoard(), click);
        Menu menu = new Menu();

        GUI gui = new GUI(display, menu);

        gui.setContentPane(display);

        int x;
        int y;
        int response = 0;

        while(true) {
            while(!click.isNewClick()) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            x = game.getBoard().pixelToX(click.getClickedX(), click.getClickedY());
            y = game.getBoard().pixelToY(click.getClickedX(), click.getClickedY());
            if(click.isLeftClick())
                response = game.getBoard().flipTile(x, y);
            else
                game.getBoard().flagTile(x, y);

            display.repaint();
            if(response == 1 || response == 2)
                break;

            click.setNewClick(false);
        }

        if(response == 1) {
            System.out.println("GAME OVER");
        } else
            System.out.println("YOU WIN");
    }

}