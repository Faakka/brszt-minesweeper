package brszta.minesweeper.gui;

import javax.swing.*;

public class GUI extends JFrame {

    private Display display;
    private Menu menu;

    public static int SPACING = 1;
    public static int TILE_SIZE = 40;
    public static int X_MARGIN = 50+TILE_SIZE;
    public static int Y_MARGIN = 2;
    public static int TEXT_SIZE = TILE_SIZE/2;
    public static int X_TEXT = TILE_SIZE/2 + TILE_SIZE/4;
    public static int Y_TEXT = TILE_SIZE/3;

    public GUI(Display display, Menu menu) {
        this.setTitle("Minesweeper");
        this.setSize(1200, 725);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);

        this.menu = menu;
        this.display = display;

        this.addMouseListener(display.getClick());
        this.setJMenuBar(menu.getMenuBar());
    }
}
