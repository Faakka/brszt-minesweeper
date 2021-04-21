package brszta.minesweeper.gui;

import brszta.minesweeper.backend.game.Board;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Display extends JPanel {

    private Board board;
    private Click click;

    public Display(Board board, Click click) {
        this.board = board;
        this.click = click;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 1600, 900);

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                g.setColor(Color.gray);

                if (board.getBoard()[i][j].isFlipped()) {  // felfedi a kockát (sárgára szinezi ahol akna van)
                    g.setColor(Color.white);
                    if (board.getBoard()[i][j].isBomb()) {
                        g.setColor(Color.red);
                    }
                }

                g.fillRect(GUI.SPACING+(j*GUI.TILE_SIZE), GUI.SPACING+(i*GUI.TILE_SIZE) + GUI.TILE_SIZE, GUI.TILE_SIZE-(2*GUI.SPACING), GUI.TILE_SIZE-(2*GUI.SPACING));
                if (board.getBoard()[i][j].isFlipped()) {
                    g.setColor(Color.black);
                    //mező felfedés
                    if (!board.getBoard()[i][j].isBomb() && board.getBoard()[i][j].getBombsNearby() != 0) {
                        switch (board.getBoard()[i][j].getBombsNearby()) {
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
                        g.drawString(Integer.toString(board.getBoard()[i][j].getBombsNearby()), (j*GUI.TILE_SIZE)+GUI.Y_TEXT, (i*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.X_TEXT);
                    }
                    //Akna kirajzolása
                    else if (board.getBoard()[i][j].isBomb()) {
                        g.drawString("X", (j*GUI.TILE_SIZE)+GUI.Y_TEXT, (i*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.X_TEXT);
                    }
                }

                // Flagek kirajzolása a cellákra--------------------------------------------------------------------

                if (board.getBoard()[i][j].isFlagged()) {
                    g.setColor(Color.YELLOW);
                    g.drawString("F", (j*GUI.TILE_SIZE)+GUI.Y_TEXT, (i*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.X_TEXT);
                }
            }
        }

/*        // WIN LOSE MSG box paint-----------------------------------------------------------------------------------

        if (gui.victory == true) {
            g.setColor(Color.GREEN);
            gui.vicMes = "You win!";
        } else if (gui.defeat == true) {
            g.setColor(Color.red);
            gui.vicMes = "You lose";
        }

        if (gui.victory == true || gui.defeat == true) {
            gui.vicMesY = -50 + (int) (new Date().getTime() - gui.endDate.getTime()) / 10;
            if (gui.vicMesY > 70) {
                gui.vicMesY = 70;
            }
            g.setFont(new Font("Tahoma", Font.PLAIN, 70));
            g.drawString(gui.vicMes, gui.vicMesX, gui.vicMesY);
        }
*/

    }

    public Click getClick() {
        return click;
    }
}
