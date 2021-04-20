package brszta.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Display extends JPanel {

    private final GUI gui;

    public Display(GUI gui) {
        this.gui = gui;
    }

    //JButton[][] buttons = new JButton[20][20];

    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 1280, 800);

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                g.setColor(Color.gray);
/*
                buttons[i][j] = new JButton();
                buttons[i][j].setBounds((i * 80) + 80, (j * 80) + 106, 80, 80);
                this.add(buttons[i][j]);/*

                /*  //Showing mines for debug
                if(mines[i][j] == 1){
                    g.setColor(Color.yellow);
                }*/
                if (gui.revealed[i][j] == true) {  // felfedi a kockát (sárgára szinezi ahol akna van)
                    g.setColor(Color.white);
                    if (gui.mines[i][j] == 1) {
                        g.setColor(Color.red);
                    }
                }

                if (((gui.mx >= (gui.spacing + (i * 80))) && ((gui.mx < ((i * 80) + 80) - gui.spacing))) && ((gui.my >= (gui.spacing + ((j * 80) + 106))) && (gui.my < (((j * 80) + 186) - (gui.spacing))))) {
                    g.setColor(Color.lightGray);
                }
                g.fillRect(gui.spacing + i * 80, gui.spacing + j * 80 + 80, 80 - 2 * gui.spacing, 80 - 2 * gui.spacing);
                if (gui.revealed[i][j] == true) {  // felfedia kockát (sárgára szinezi)
                    g.setColor(Color.black);
                    //mező felfedés
                    if (gui.mines[i][j] == 0 && gui.neighbours[i][j] != 0) {
                        switch (gui.neighbours[i][j]) {
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


                        g.setFont(new Font("Tahoma", Font.BOLD, 40));
                        g.drawString(Integer.toString(gui.neighbours[i][j]), i * 80 + 27, (j * 80) + 80 + 55);
                    }
                    //Akna kirajzolása
                    else if (gui.mines[i][j] == 1) {
                        g.fillRect(i * 80 + 10 + 20, j * 80 + 80 + 20, 20, 40);
                        g.fillRect(i * 80 + 20, j * 80 + 80 + 10 + 20, 40, 20);
                        g.fillRect(i * 80 + 5 + 20, j * 80 + 80 + 5 + 20, 30, 30);
                        g.fillRect(i * 80 + 38, j * 80 + 80 + 15, 4, 50);
                        g.fillRect(i * 80 + 15, j * 80 + 80 + 38, 50, 4);
                    }
                }

                // Flagek kirajzolása a cellákra--------------------------------------------------------------------

                if (gui.flagged[i][j] == true) {
                    g.setColor(Color.black);
                    g.fillRect(i * 80 + 32 + 5, j * 80 + 80 + 15 + 5, 5, 40);
                    g.fillRect(i * 80 + 20 + 5, j * 80 + 80 + 50 + 5, 30, 10);
                    g.setColor(Color.red);
                    g.fillRect(i * 80 + 16 + 5, j * 80 + 80 + 15 + 5, 20, 15);
                    g.setColor(Color.BLACK);
                    g.drawRect(i * 80 + 16 + 5, j * 80 + 80 + 15 + 5, 20, 15);
                    g.drawRect(i * 80 + 17 + 5, j * 80 + 80 + 16 + 5, 18, 13);
                    g.drawRect(i * 80 + 18 + 5, j * 80 + 80 + 17 + 5, 16, 11);
                }

            }
        }

        //spacing plus-minus button painting

        g.setColor(Color.black);
        g.fillRect(gui.spacingX, gui.spacingY, 300, 60);

        g.setColor(Color.white);
        g.fillRect(gui.minusX + 5, gui.minusY + 10, 40, 40);
        g.fillRect(gui.plusX + 5, gui.plusY + 10, 40, 40);

        g.setFont(new Font("Tahoma", Font.PLAIN, 35));
        g.drawString("Spacing", gui.spacingX + 20, gui.spacingY + 45);

        // - jel
        g.setColor(Color.black);
        g.fillRect(gui.minusX + 15, gui.minusY + 27, 20, 6);

        // + jel
        g.fillRect(gui.plusX + 15, gui.plusY + 27, 20, 6);
        g.fillRect(gui.plusX + 22, gui.plusY + 20, 6, 20);

        //Spacing kiírása
        g.setColor(Color.white);
        g.setFont(new Font("Tahoma", Font.PLAIN, 30));
        if (gui.spacing < 10) {
            g.drawString("0" + Integer.toString(gui.spacing), gui.minusX + 49, gui.minusY + 40);
        } else {
            g.drawString(Integer.toString(gui.spacing), gui.minusX + 49, gui.minusY + 40);
        }

        /*
        g.setColor(Color.black);
        g.fillRect(plusX, plusY, 60, 60);
        g.setColor(Color.white);
        */


        //Smiley kirajzolása----------------------------------------------------------------------------------------
        g.setColor(Color.yellow);
        g.fillOval(gui.smileX, gui.smileY, 70, 70);
        g.setColor(Color.black);
        g.fillOval(gui.smileX + 15, gui.smileY + 20, 10, 10);
        g.fillOval(gui.smileX + 45, gui.smileY + 20, 10, 10);
        // Mosoly rajzolás
        if (gui.happiness == true) {
            g.fillRect(gui.smileX + 20, gui.smileY + 50, 30, 5);
            g.fillRect(gui.smileX + 17, gui.smileY + 45, 5, 5);
            g.fillRect(gui.smileX + 48, gui.smileY + 45, 5, 5);
        } else {
            g.fillRect(gui.smileX + 20, gui.smileY + 45, 30, 5);
            g.fillRect(gui.smileX + 17, gui.smileY + 50, 5, 5);
            g.fillRect(gui.smileX + 48, gui.smileY + 50, 5, 5);
        }

        //Flagger menüpont kirajzolása------------------------------------------------------------------------------

        g.setColor(Color.black);
        g.fillRect(gui.flaggerX + 32, gui.flaggerY + 15, 5, 40);
        g.fillRect(gui.flaggerX + 20, gui.flaggerY + 50, 30, 10);
        g.setColor(Color.red);
        g.fillRect(gui.flaggerX + 16, gui.flaggerY + 15, 20, 15);
        g.setColor(Color.BLACK);
        g.drawRect(gui.flaggerX + 16, gui.flaggerY + 15, 20, 15);
        g.drawRect(gui.flaggerX + 17, gui.flaggerY + 16, 18, 13);
        g.drawRect(gui.flaggerX + 18, gui.flaggerY + 17, 16, 11);

        if (gui.flagger == true) {
            g.setColor(Color.red);
        }

        g.drawOval(gui.flaggerX, gui.flaggerY, 70, 70);
        g.drawOval(gui.flaggerX + 1, gui.flaggerY + 1, 68, 68);
        g.drawOval(gui.flaggerX + 2, gui.flaggerY + 2, 66, 66);


        //timer counter painting------------------------------------------------------------------------------------
        g.setColor(Color.black);
        g.fillRect(gui.timeX, gui.timeY, 140, 70);
        if (gui.defeat == false && gui.victory == false) {
            gui.sec = (int) ((new Date().getTime() - gui.startDate.getTime()) / 1000);  // millisecond az alap
        }
        if (gui.sec > 999) {
            gui.sec = 999;
        }
        g.setColor(Color.white);
        if (gui.victory == true) {
            g.setColor(Color.green);
        } else if (gui.defeat == true) {
            g.setColor(Color.red);
        }
        g.setFont(new Font("Tahoma", Font.PLAIN, 80));
        if (gui.sec < 10) {
            g.drawString("00" + Integer.toString(gui.sec), gui.timeX, gui.timeY + 65);
        } else if (gui.sec < 100) {
            g.drawString("0" + Integer.toString(gui.sec), gui.timeX, gui.timeY + 65);
        } else {
            g.drawString(Integer.toString(gui.sec), gui.timeX, gui.timeY + 65);
        }

        // WIN LOSE MSG box paint-----------------------------------------------------------------------------------

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


    }
}
