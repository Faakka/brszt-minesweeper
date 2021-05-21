package brszta.minesweeper;

import brszta.minesweeper.backend.game.Highscores;
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
                            controller.sleepInMs(500);
                            host.udpSendObject(game, host.getClientIpAddress());
                            host.udpSendObject(game, host.getClientIpAddress());
                            controller.sleepInMs(500);
                            game.setStartTime();
                            new SecondsTask(controller, display);
                            click.setNewClick(false);
                            controller.setNewGame(false);
                            controller.setRunning(true);
                            //System.out.println(game.getBoard().getNumOfBombs());
                            //System.out.println(game.getStartTime());
                        }
                        if (controller.isRunning() && !controller.isNewGame()) {
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
                    System.out.println("Cliens ágba lep 1");

                    if (!client.isAlive()){
                        client.start(); // Client start receiving
                        System.out.println("Cliens fut 2");
                    }
                    if(!controller.isConnected()){
                        System.out.println("Cliens csatlakozasi ag 3");
                        if(client.connectToHost(controller.getIpToConnect())){
                            System.out.println("Cliens csatlakozott beállit 4");
                            controller.setConnected(true);
                            menu.clientWindowDisposeTimer.start();
                            controller.setNewGame(true);
                            controller.sleepInMs(500);
                        }
                    }
                    if(controller.isConnected()){
                        System.out.println("Cliens mar csatlakzott ag 5");
                        if(controller.isNewGame()){
                            System.out.println("Uj játék ágba belepett 6");

                            game = client.getInputGame();
                            display.setGame(game);
                            System.out.println("Megkaptam a gamet a servertol 7");
                            controller.setNewBoard(false);
                            controller.setRunning(true);
                            display.repaint();
                            game.setStartTime();
                            client.udpSendObject(game, client.getHostIpAddress());
                            System.out.println(game.getStartTime());
                            new SecondsTask(controller, display);
                            click.setNewClick(false);
                            controller.setNewGame(false);
                            display.repaint();
                        }
                        if(controller.isRunning() && !controller.isNewGame()){
                            System.out.println("Mat fut a jatek 8");
                            display.repaint();
                            gameStatus = controller.playGame(game, display, click);
                            display.repaint();
                            System.out.println("Play game utan 8");
                            client.udpSendObject(game, client.getHostIpAddress());




                            if (gameStatus == 2) {
                                game.calcGameTime();
                                Score score = new Score("", game.getLevel(), game.getGameTime());
                                new InsertData(score);
                                controller.setRunning(false);
                                controller.setMultiplayer(false);
                                controller.setNewGame(false);
                                System.out.println("A jatek veget ert  nyeressel 9");
                            }
                        }
                        else {
                            controller.sleepInMs(50);
                        }

                    }

                }
            // single player
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
            display.repaint();
        }//main loop
    }
}