package brszta.minesweeper.gui;

import javax.swing.*;
import java.util.Date;
import java.util.Random;

public class GUI extends JFrame {

    //Resetter
    public boolean resetter = false;

    //idő funkció
    Date startDate = new Date();
    Date endDate;

    // timer pozicioja
    public int timeX = 1120;
    public int timeY = 5;

    //Spacing
    public int spacingX = 90;
    public int spacingY = 10;

    //Button poziciok
    public int minusX = spacingX + 160;
    public int minusY = spacingY;

    public int plusX = spacingX + 240;
    public int plusY = spacingY;

    // Message Box pozició
    public int vicMesX = 700;
    public int vicMesY = -50;

    String vicMes = "Nothing yet";

    //csak másodperceket tarol
    public int sec = 0;
    int spacing = 5;
    int neighs = 0;

    //Egér kezdeti pozició
    public int mx = -100;
    public int my = -100;

    //smiley pozicio változok
    public int smileX = 605;  // számítani kéne inkább
    public int smileY = 0;
    public int smileyCenterX = smileX + 35;
    public int smileyCenterY = smileY + 35;

    // Flagek
    public int flaggerX = 445;
    public int flaggerY = 5;
    public int flaggerCenterX = flaggerX + 35;
    public int flaggerCenterY = flaggerY + 35;
    public boolean flagger = false;

    //Játék állapotok
    public boolean happiness = true;
    public boolean victory = false;
    public boolean defeat = false;

    Random rand = new Random();

    int[][] mines = new int[16][9];
    int[][] neighbours = new int[16][9];
    boolean[][] revealed = new boolean[16][9];
    boolean[][] flagged = new boolean[16][9];


    public GUI() {
        this.setTitle("Minesweeper");
        this.setSize(1286, 834);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (rand.nextInt(100) < 20) {  // Aknák % arányosan vannak
                    mines[i][j] = 1;
                } else {
                    mines[i][j] = 0;
                }
                revealed[i][j] = false;
            }
        }

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                neighs = 0;
                for (int m = 0; m < 16; m++) {
                    for (int n = 0; n < 9; n++) {
                        if (!((m == i) && (n == j))) {
                            if (isN(i, j, m, n) == true) {
                                neighs++;
                            }
                        }
                    }
                }
                neighbours[i][j] = neighs;
            }
        }

        Menu menu = new Menu(this);
        this.resetAll();

        Display display = new Display(this);
        this.setContentPane(display);

        Click click = new Click(this);
        this.addMouseListener(click);
    }

    public void checkVictoryStates() {

        if (defeat == false) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 9; j++) {
                    if (mines[i][j] == 1) {
                        if (revealed[i][j] == true && mines[i][j] == 1) {
                            defeat = true;
                            happiness = false;
                            endDate = new Date();
                        }
                    }
                }
            }
        }

        if (totalBoxesRevealed() >= 144 - totalMines() && victory == false) {
            victory = true;
            endDate = new Date();
        }

    }

    // Megszámolja az aknákat és a felfedett cellákat
    public int totalMines() {
        int total = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (mines[i][j] == 1) {
                    total++;
                }
            }
        }
        return total;
    }

    public int totalBoxesRevealed() {
        int total = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (revealed[i][j] == true) {
                    total++;
                }
            }
        }
        return 0;
    }


    //Reset method------------------------------------------------------------------------------------------------------
    public void resetAll() {

        resetter = true;
        startDate = new Date();

        flagger = false;

        vicMesY = -50;

        happiness = true;
        victory = false;
        defeat = false;


        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (rand.nextInt(100) < 20) {  // Aknák % arányosan vannak
                    mines[i][j] = 1;
                } else {
                    mines[i][j] = 0;
                }
                revealed[i][j] = false;
                flagged[i][j] = false;
            }
        }

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                neighs = 0;
                for (int m = 0; m < 16; m++) {
                    for (int n = 0; n < 9; n++) {
                        if (!((m == i) && (n == j))) {
                            if (isN(i, j, m, n) == true) {
                                neighs++;
                            }
                        }
                    }
                }
                neighbours[i][j] = neighs;
            }
        }
        resetter = false;
    }

    // Smiley click terület számítás------------------------------------------------------------------------------------
    public boolean inSmiley() {  // nem pontos
        int dif = (int) Math.sqrt((Math.abs(mx - (smileyCenterX + 10)) * Math.abs(mx - (smileyCenterX + 10))) + (Math.abs(my - (smileyCenterY + 30)) * Math.abs(my - (smileyCenterY + 30))));
        if (dif < 35) {
            return true;
        }
        return false;
    }

    // Flagger területre kattintás szamítása----------------------------------------------------------------------------
    public boolean inFlagger() {  // nem pontos
        int dif = (int) Math.sqrt((Math.abs(mx - (flaggerCenterX + 10)) * Math.abs(mx - (flaggerCenterX + 10)) + (Math.abs(my - (flaggerCenterY + 30)) * Math.abs(my - (flaggerCenterY + 30)))));
        if (dif < 35) {
            return true;
        }
        return false;
    }


    // visszadja hol van az egér a global mx my változokat használja
    public int inBoxX() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (((mx >= (spacing + (i * 80))) && ((mx < ((i * 80) + 80) - spacing))) && ((my >= (spacing + ((j * 80) + 106))) && (my < (((j * 80) + 186) - (spacing))))) {
                    return i;
                }
            }
        }
        return (-1);
    }

    public int inBoxY() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (((mx >= (spacing + (i * 80))) && ((mx < ((i * 80) + 80) - spacing))) && ((my >= (spacing + ((j * 80) + 106))) && (my < (((j * 80) + 186) - (spacing))))) {
                    return j;
                }
            }
        }
        return (-1);
    }

    public boolean isN(int mX, int mY, int cX, int cY) {
        if ((mX - cX) < 2 && (mX - cX) > -2 && (mY - cY) < 2 && (mY - cY) > -2 && ((mines[cX][cY]) == 1)) {
            return true;
        }
        return false;
    }

}
