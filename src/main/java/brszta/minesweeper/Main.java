package brszta.minesweeper;

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

        Click click = new Click();
        Display display = new Display(game, click);
        Menu menu = new Menu(controller, game);

        GUI gui = new GUI(display, menu);
        gui.setContentPane(display);

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
                controller.playGame(game, display, click);
            }

            else{
                controller.sleepInMs(50);
            }

        }

    }
}