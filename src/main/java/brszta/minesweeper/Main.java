package brszta.minesweeper;

import brszta.minesweeper.backend.Controller;
import brszta.minesweeper.backend.game.Score;
import brszta.minesweeper.backend.io.JsonIO;
import brszta.minesweeper.gui.*;
import brszta.minesweeper.backend.game.Board;
import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.network.UDPClient;
import brszta.minesweeper.network.UDPServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Game game = new Game();
        Controller controller = new Controller();
        game.setLevel(1);
        game.setBoard(new Board(1));
        game.getBoard().generate();
        UDPClient client = new UDPClient();
        UDPServer host = new UDPServer();

        int counter=0;

        JsonIO.readScores(1);
        JsonIO.readScores(2);
        JsonIO.readScores(3);

        Click click = new Click();
        Display display = new Display(game, click, host, client, controller);
        Menu menu = new Menu(controller, game);

        GUI gui = new GUI(display, menu);
        gui.setContentPane(display);

        int gameStatus;

        while (true) {

            if (controller.isMultiplayer()) { //Multiplayer
                controller.sleepInMs(10);

                // HOST game
                if(controller.isHost()){
                    if(!host.isAlive()){
                        host.start(); // host start receiving
                    }
                    if(!controller.isConnected()){
                        host.startHost();
                        if(host.getIsClientConnected()){
                            controller.setConnected(true);
                            menu.hostWindowDisposeTimer.start();
                            controller.setNewGame(true);
                            controller.sleepInMs(100);
                        }
                    }
                    if(controller.isConnected()){
                        if(controller.isNewGame()){
                            game.setBoard(new Board(game.getLevel()));
                            game.getBoard().generate();
                            game.setYouWon(false);
                            game.setGameOver(false);

                            controller.sleepInMs(500);
                            host.udpSendObject(game, host.getClientIpAddress());
                            host.udpSendObject(game, host.getClientIpAddress());
                            controller.sleepInMs(500);
                            game.setStartTime();
                            new SecondsTask(controller, display);
                            click.setNewClick(false);
                            controller.setNewGame(false);
                            controller.setRunning(true);

                        }
                        if (controller.isRunning() && !controller.isNewGame()) {
                            if((host.getInputGame().getGameStat()) == 1){
                                controller.setRunning(false);
                                controller.setMultiplayer(false);
                                controller.setNewGame(false);
                                game.setYouWon(true);
                            }
                            gameStatus = controller.playGame(game, display, click);
                            host.udpSendObject(game, host.getClientIpAddress());

                            if (gameStatus == 2) {
                                game.calcGameTime();
                                Score score = new Score("", game.getLevel(), game.getGameTime());
                                new InsertData(score);
                                controller.setRunning(false);
                                controller.setMultiplayer(false);
                                controller.setNewGame(false);
                            }
                        }
                        else {
                            controller.sleepInMs(50);
                        }

                    }

                }
                //Client game
                else{
                    if (!client.isAlive()){
                        client.start(); // Client start receiving
                    }
                    if(!controller.isConnected()){
                        if(client.connectToHost(controller.getIpToConnect())){
                            controller.setConnected(true);
                            menu.clientWindowDisposeTimer.start();
                            controller.setNewGame(true);
                            controller.sleepInMs(500);
                        }
                    }
                    if(controller.isConnected()){
                        if(controller.isNewGame()){
                            game.setYouWon(false);
                            game.setGameOver(false);
                            game = client.getInputGame();
                            display.setGame(game);
                            controller.setNewBoard(false);
                            controller.setRunning(true);
                            display.repaint();
                            game.setStartTime();
                            client.udpSendObject(game, client.getHostIpAddress());
                            client.udpSendObject(game, client.getHostIpAddress());
                            new SecondsTask(controller, display);
                            click.setNewClick(false);
                            controller.setNewGame(false);
                            display.repaint();
                        }
                        if(controller.isRunning() && !controller.isNewGame()){
                            if((client.getInputGame().getGameStat()) == 1){
                                controller.setRunning(false);
                                controller.setMultiplayer(false);
                                controller.setNewGame(false);
                                game.setYouWon(true);
                            }
                            display.repaint();
                            gameStatus = controller.playGame(game, display, click);
                            display.repaint();
                            client.udpSendObject(game, client.getHostIpAddress());

                            if (gameStatus == 2) {
                                game.calcGameTime();
                                Score score = new Score("", game.getLevel(), game.getGameTime());
                                new InsertData(score);
                                controller.setRunning(false);
                                controller.setMultiplayer(false);
                                controller.setNewGame(false);
                            }
                        }
                        else {
                            controller.sleepInMs(50);
                        }

                    }

                }
            // single player
            } else {
                while (controller.isMultiplayer() == false) {
                    if (controller.isNewBoard()) {
                        game.setBoard(new Board(game.getLevel()));
                        game.getBoard().generate();
                        game.setYouWon(false);
                        game.setGameOver(false);
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
            display.repaint();
        }//main loop
    }
}