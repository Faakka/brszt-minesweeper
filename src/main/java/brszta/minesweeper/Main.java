package brszta.minesweeper;

import brszta.minesweeper.backend.Controller;
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

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
/*

        Game game = new Game();
        Controller controller = new Controller();
        game.setType("single");
        game.setLevel(1);
        game.setBoard(new Board(1));
        game.getBoard().generate();

        Click click = new Click();
        Display display = new Display(game.getBoard(), click);  // sorrendi hiba lehet
        Menu menu = new Menu(controller, game);

        GUI gui = new GUI(display, menu);

        gui.setContentPane(display);

        int x;
        int y;
        int response = 0;

        while(controller.isRunning()) {
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
*/
//--------------------------------------------------


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

        int x;
        int y;
        int response = 0;

        while(true){
            //fut
            if(controller.isNewBoard()){
                game.setBoard(new Board(game.getLevel()));
                game.getBoard().generate();
                controller.setNewBoard(false);
                controller.setRunning(true);
                game.setStartTime();
                new SecondsTask(controller, display);
            }
            else if( controller.isRunning()){
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

                click.setNewClick(false);
            }

            if(response == 1) {
                System.out.println("GAME OVER");
                controller.setRunning(false);
                response = 0;
            } else if(response == 2){
                System.out.println("YOU WIN");
                response = 0;
            }

            else{
                display.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }







    /*
    * 1. csak frame megjelenítés
    * 2. Level set --> runing alatt ne lehessen állitani
    * 3. New game kell : 1game 2. game.setboard(new Board(game.getlevel()))  3. game.getboard().generate  4. gui.display
    *
    * */

    }
}