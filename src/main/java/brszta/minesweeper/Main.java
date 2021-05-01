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

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Controller controller = new Controller();
        game.setLevel(1);
        game.setBoard(new Board(game.getLevel()));
        game.getBoard().generate();

        Click click = new Click();
        Display display = new Display(game.getBoard());  // sorrendi hiba lehet
        Menu menu = new Menu(controller, game);

        GUI gui = new GUI(display, menu, click);
        gui.setContentPane(display);

        while(true){

            if(controller.isNewBoard()){
                game.setBoard(new Board(game.getLevel()));
                game.getBoard().generate();
                display.setBoard(game.getBoard());
                controller.setNewBoard(false);
                controller.setRunning(true);
                click.setNewClick(false);
                display.repaint();
            }
            else if(controller.isRunning()){
                controller.playGame(game, display, click);

            } else {
                controller.sleepInMs(50);
            }

        }

        /*
        - kulon sleep keszitese a Controllerbe, az atlathatobb kodert
        - jatek vegen setRunning(false)
        - mielott belelep az isRunning agba, setNewClick(false)
        - jatek vegen respone = 0
        - Display osztalybol click mezo torolve, nem volt hasznalva
        - GUI class constructornak click obj-t is at kell vennie, ha a Displaybol toroljuk
        - playGame kulon Controller metodusba  szervezese az atlathato vezerlesi szerkezet miatt
         */





    /*
    * 1. csak frame megjelenítés
    * 2. Level set --> runing alatt ne lehessen állitani
    * 3. New game kell : 1game 2. game.setboard(new Board(game.getlevel()))  3. game.getboard().generate  4. gui.display
    *
    * */

    }
}