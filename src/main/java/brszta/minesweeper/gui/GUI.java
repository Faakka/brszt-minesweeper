package brszta.minesweeper.gui;

import javax.swing.*;
import java.util.Date;
import java.util.Random;

public class GUI extends JFrame {

    private Display display;
    private Menu menu;

    public static int SPACING = 2;
    public static int TILE_SIZE = 40;
    public static int X_MARGIN = 50+TILE_SIZE;
    public static int Y_MARGIN = 2;
    public static int TEXT_SIZE = TILE_SIZE/2;
    public static int X_TEXT = TILE_SIZE/2 + TILE_SIZE/4;
    public static int Y_TEXT = TILE_SIZE/3;

    public GUI(Display display, Menu menu, Click click) {
        this.setTitle("Minesweeper");
        this.setSize(1200, 725);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);

        this.menu = menu;
        this.display = display;

        this.addMouseListener(click);
        this.setJMenuBar(menu.getMenuBar());
    }
}
