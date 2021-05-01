package brszta.minesweeper;

import brszta.minesweeper.backend.game.Highscores;
import brszta.minesweeper.backend.game.Score;
import brszta.minesweeper.backend.io.JsonIO;
import brszta.minesweeper.gui.*;
import brszta.minesweeper.backend.game.Board;
import brszta.minesweeper.backend.game.Game;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Controller controller = new Controller();
        game.setLevel(1);
        game.setBoard(new Board(1));
        game.getBoard().generate();

        JsonIO.readScores(1);
        JsonIO.readScores(2);
        JsonIO.readScores(3);

        Click click = new Click();
        Display display = new Display(game, click);
        Menu menu = new Menu(controller, game);

        GUI gui = new GUI(display, menu);
        gui.setContentPane(display);

        int gameStatus;

        while(true){
            if(controller.isNewBoard()){
                game.setBoard(new Board(game.getLevel()));
                game.getBoard().generate();
                controller.setNewBoard(false);
                controller.setRunning(true);
                game.setStartTime();
                new SecondsTask(controller, display);
                click.setNewClick(false);
            }
            else if(controller.isRunning()){
                gameStatus = controller.playGame(game, display, click);

                if(gameStatus == 2) {
                    game.calcGameTime();
                    Score score = new Score("", game.getLevel(), game.getGameTime());
                    new InsertData(score);
                }
            }

            else{
                controller.sleepInMs(50);
            }

        }

    }
}