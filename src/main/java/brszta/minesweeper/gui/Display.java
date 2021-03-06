package brszta.minesweeper.gui;

import brszta.minesweeper.backend.Controller;
import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.network.UDPClient;
import brszta.minesweeper.network.UDPServer;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {

    private Click click;
    private Game game;
    private UDPClient client;
    private UDPServer host;
    private Controller controller;


    private int timeX = 0;
    private int timeY = 2;

    public Display(Game game, Click click, UDPServer host, UDPClient client, Controller controller) {
        this.click = click;
        this.game = game;
        this.client = client;
        this.host = host;
        this.controller= controller;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 1600, 900);
        g.setFont(new Font("Tahoma", Font.BOLD, GUI.TEXT_SIZE));

        for (int i = 0; i < game.getBoard().getHeight(); i++) {
            for (int j = 0; j < game.getBoard().getWidth(); j++) {
                g.setColor(Color.gray);

                if (game.getBoard().getBoard()[i][j].isFlipped()) {
                    g.setColor(Color.white);
                    if (game.getBoard().getBoard()[i][j].isBomb()) {
                        g.setColor(Color.red);
                    }
                }

                g.fillRect(GUI.SPACING+(j*GUI.TILE_SIZE), GUI.SPACING+(i*GUI.TILE_SIZE) + GUI.TILE_SIZE, GUI.TILE_SIZE-(2*GUI.SPACING), GUI.TILE_SIZE-(2*GUI.SPACING));
                if (game.getBoard().getBoard()[i][j].isFlipped()) {
                    g.setColor(Color.black);

                    if (!game.getBoard().getBoard()[i][j].isBomb() && game.getBoard().getBoard()[i][j].getBombsNearby() != 0) {
                        switch (game.getBoard().getBoard()[i][j].getBombsNearby()) {
                            case 1:
                                g.setColor(Color.blue);
                                break;
                            case 2:
                                g.setColor(Color.green);
                                break;
                            case 3:
                                g.setColor(Color.red);
                                break;
                            case 4:
                                g.setColor(new Color(0, 0, 128));
                                break;
                            case 5:
                                g.setColor(new Color(178, 34, 34));
                                break;      // Firebrick
                            case 6:
                                g.setColor(new Color(72, 209, 204));
                                break; // Medium Turquoise
                            case 7:
                                g.setColor(Color.black);
                                break;
                            case 8:
                                g.setColor(Color.DARK_GRAY);
                                break;
                            default:
                                break;

                        }
                        g.drawString(Integer.toString(game.getBoard().getBoard()[i][j].getBombsNearby()), (j*GUI.TILE_SIZE)+GUI.Y_TEXT, (i*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.X_TEXT);
                    }

                    else if (game.getBoard().getBoard()[i][j].isBomb() && !game.getBoard().getBoard()[i][j].isFlagged()) {
                        g.drawString("X", (j*GUI.TILE_SIZE)+GUI.Y_TEXT, (i*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.X_TEXT);
                    }
                }

                if (game.getBoard().getBoard()[i][j].isFlagged()) {
                    g.setColor(Color.YELLOW);
                    g.drawString("F", (j*GUI.TILE_SIZE)+GUI.Y_TEXT, (i*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.X_TEXT);
                }
            }
        }

        g.setColor(Color.white);
        g.setFont(new Font("Tahoma", Font.PLAIN, 30));
        g.drawString(game.formattedGameTime(), timeX ,timeY+32);
        g.drawString(game.getBoard().formattedProgress(), timeX + 100, timeY+32);


        g.setColor(Color.YELLOW);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString("F:", timeX + 230, timeY+32);

        g.setColor(Color.white);
        g.setFont(new Font("Tahoma", Font.PLAIN, 30));
        g.drawString("" + game.getBoard().getNumOfFlags(), timeX + 265, timeY+32);

        //Other player score
        if(controller.isMultiplayer()) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Tahoma", Font.BOLD, 30));
            g.drawString("Player2 Score:", timeX + 350, timeY + 32);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Tahoma", Font.PLAIN, 30));
        if(controller.isMultiplayer()){
            if(host.isAlive() && controller.isRunning()){
                g.drawString(host.getInputGame().getBoard().formattedProgress(),timeX + 600, timeY+32);
            }
            else if(client.isAlive() && controller.isRunning()){
                g.drawString(client.getInputGame().getBoard().formattedProgress(),timeX + 600, timeY+32);
            }
        }

        if(game.isYouWon()){
            g.setColor(Color.GREEN);
            g.setFont(new Font("Tahoma", Font.BOLD, 40));
            g.drawString("You Win!", timeX + 450, timeY + 100);
        }
        if(game.isGameOver()){
            g.setColor(Color.BLACK);
            g.setFont(new Font("Tahoma", Font.BOLD, 40));
            g.drawString("You Loose!", timeX + 450, timeY+100);
        }


    }

    public Game getGame() {
        return game;
    }

    public Click getClick() {
        return click;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
