package brszta.minesweeper;

import brszta.minesweeper.backend.game.Highscores;
import brszta.minesweeper.backend.game.Score;
import brszta.minesweeper.backend.io.JsonIO;
import brszta.minesweeper.gui.*;
import brszta.minesweeper.backend.game.Board;
import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.network.UDPClient;
import brszta.minesweeper.network.UDPServer;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Controller controller = new Controller();
        game.setLevel(1);
        game.setBoard(new Board(1));
        game.getBoard().generate();
        UDPServer host = new UDPServer();
        UDPClient client = new UDPClient();
        int counter=0;


        JsonIO.readScores(1);
        JsonIO.readScores(2);
        JsonIO.readScores(3);

        Click click = new Click();
        Display display = new Display(game, click);
        Menu menu = new Menu(controller, game);

        GUI gui = new GUI(display, menu);
        gui.setContentPane(display);

        int gameStatus;



        while (true) {

            if (controller.isMultiplayer()) {
                controller.sleepInMs(10);
                if(controller.isHost()){
                    if(!host.isAlive()){
                        host.start(); // host start receiving
                    }
                    if(!controller.isConnected()){
                        host.startHost();
                        if(host.getIsClientConnected()){
                            controller.setConnected(true);
                        }
                    }
                    if(controller.isConnected()){
                        System.out.println("other player is connected");
                    }


                }
                else{//client
                    if (!client.isAlive()){
                        client.start(); // Client start receiving
                    }
                    if(!controller.isConnected()){
                        if(client.connectToHost(controller.getIpToConnect())){
                            controller.setConnected(true);
                        }
                        if(controller.isConnected()){
                            System.out.println("Connected to host");
                        }

                    }

                }

            } else {

                while (true && controller.isMultiplayer() == false) {
                    if (controller.isNewBoard()) {
                        game.setBoard(new Board(game.getLevel()));
                        game.getBoard().generate();
                        controller.setNewBoard(false);
                        controller.setRunning(true);
                        game.setStartTime();
                        new SecondsTask(controller, display);
                        click.setNewClick(false);
                    } else if (controller.isRunning()) {
                        gameStatus = controller.playGame(game, display, click);

                        if (gameStatus == 2) {
                            game.calcGameTime();
                            Score score = new Score("", game.getLevel(), game.getGameTime());
                            new InsertData(score);
                        }
                    } else {
                        controller.sleepInMs(50);
                    }

                }

            }

        }//main loop
    }
}