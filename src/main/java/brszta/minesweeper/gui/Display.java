package brszta.minesweeper.gui;

import brszta.minesweeper.backend.game.Board;
import brszta.minesweeper.backend.game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Display extends JPanel {

    private Click click;
    private Game game;
    private int timeX = 0;
    private int timeY = 2;

    public Display(Game game, Click click) {
        this.click = click;
        this.game = game;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 1600, 900);


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

                        g.setFont(new Font("Tahoma", Font.BOLD, GUI.TEXT_SIZE));
                        g.drawString(Integer.toString(game.getBoard().getBoard()[i][j].getBombsNearby()), (j*GUI.TILE_SIZE)+GUI.Y_TEXT, (i*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.X_TEXT);
                    }

                    else if (game.getBoard().getBoard()[i][j].isBomb()) {
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
        g.setFont(new Font("Tahoma", Font.PLAIN, 40));
            g.drawString(game.formattedGameTime(), timeX ,timeY+32);
    }

    public Game getGame() {
        return game;
    }

    public Click getClick() {
        return click;
    }
}
