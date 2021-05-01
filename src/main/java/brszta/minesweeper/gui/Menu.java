package brszta.minesweeper.gui;

import brszta.minesweeper.backend.Controller;
import brszta.minesweeper.backend.game.Game;

import javax.swing.*;
import java.awt.event.*;


public class Menu implements ActionListener{

    private JMenuBar menuBar;
    private JMenu menu, menu2, menu3;
    private JMenu subMenu, subMenu3;
    private JMenuItem menuItem1, menuItem2, menu2Item1, menu2Item2,menu2Item3, menu3Item1;
    private JCheckBoxMenuItem checkItem1, checkItem2, checkItem3, check3Item1, check3Item2;

    private JFrame textframe, textframe2E, textframe2M, textframe2H, textframe3;
    private JPanel jp3 = new JPanel();
    private JLabel jl3 = new JLabel();
    private JPanel jp = new JPanel();
    private JTextField jt3 = new JTextField("Default", 30);
    private JButton jb = new JButton("Enter");
    private JTextArea jm = new JTextArea("Balogh Botond, Parragh Benedek, Péntek Róbert");
    private Controller controller;
    private Game game;

    public Menu(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;

        menuBar = new JMenuBar();

        //-------GAME MENU BEGINS
        menu = new JMenu("Game");
        menuBar.add(menu);

        menuItem1 = new JMenuItem("New game");
        menuItem1.addActionListener(this);
        menu.add(menuItem1);

        subMenu =new JMenu("Level option");
        menu.add(subMenu);
        checkItem1 = new JCheckBoxMenuItem("Easy");
        checkItem1.addActionListener(this);
        subMenu.add(checkItem1);
        checkItem2 = new JCheckBoxMenuItem("Medium");
        checkItem2.addActionListener(this);
        subMenu.add(checkItem2);
        checkItem3 = new JCheckBoxMenuItem("Hard");
        checkItem3.addActionListener(this);
        subMenu.add(checkItem3);

        menuItem2 = new JMenuItem("Creators");
        menuItem2.addActionListener(this);
        menu.add(menuItem2);
        //-------GAME MENU END

        //-------RES MENU BEGIN
        menu2 = new JMenu("Results");
        menuBar.add(menu2);
        menu2Item1 = new JMenuItem("Easy");
        menu2Item1.addActionListener(this);
        menu2.add(menu2Item1);
        menu2Item2 = new JMenuItem("Medium");
        menu2Item2.addActionListener(this);
        menu2.add(menu2Item2);
        menu2Item3 = new JMenuItem("Hard");
        menu2Item3.addActionListener(this);
        menu2.add(menu2Item3);
        //-------RES MENU END

        //-------CON. MENU BEGIN
        menu3 = new JMenu("Connection");
        menuBar.add(menu3);

        subMenu3 =new JMenu("PC status");
        menu3.add(subMenu3);
        check3Item1 = new JCheckBoxMenuItem("Master");
        check3Item1.addActionListener(this);
        subMenu3.add(check3Item1);
        check3Item2 = new JCheckBoxMenuItem("Slave");
        check3Item2.addActionListener(this);
        subMenu3.add(check3Item2);

        menu3Item1 = new JMenuItem("Add IP");
        menu3Item1.addActionListener(this);
        menu3.add(menu3Item1);
        //-------CON. MENU END
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem1){
            System.out.println("New game generated");
            controller.setNewBoard(true);
        }
        if (e.getSource() == menuItem2){
            System.out.println("Creators");
            textframe = new JFrame("Developers");
            textframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe.setVisible(true);
            textframe.setSize(400,200);

            jp.add(jm);
            jm.addNotify();
            textframe.add(jp);
        }
        if (e.getSource() == checkItem1 && checkItem1.isSelected()){ //EASY
            System.out.println("You have selected Easy level: 9x9");
            game.setLevel(1);

        }

        if (e.getSource() == checkItem2 && checkItem2.isSelected()){ //MEDIUM
            System.out.println("You have selected Medium level: 16x16");
            game.setLevel(2);
        }

        if (e.getSource() == checkItem3 && checkItem3.isSelected()){ //HARD
            System.out.println("You have selected Hard level: 16x30");
            game.setLevel(3);
        }

        if (e.getSource() == menu2Item1){
            System.out.println("Easy level results");
            textframe2E = new JFrame("Beginner level");
            textframe2E.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe2E.setVisible(true);
            textframe2E.setSize(200,400);
        }
        if (e.getSource() == menu2Item2){
            System.out.println("Medium level results");
            textframe2M = new JFrame("Advanced level");
            textframe2M.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe2M.setVisible(true);
            textframe2M.setSize(200,400);

        }
        if (e.getSource() == menu2Item3){
            System.out.println("Hard level results");
            textframe2H = new JFrame("Expert level");
            textframe2H.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe2H.setVisible(true);
            textframe2H.setSize(200,400);

        }

        if (e.getSource() == check3Item1 && check3Item1.isSelected()){ //MASTER
            System.out.println("You are a mester");
        }
        if (e.getSource() == check3Item2 && check3Item2.isSelected()){ //SLAVE
            System.out.println("You are a slave");
        }

        if (e.getSource() == menu3Item1){
            System.out.println("Add IP");
            textframe3 = new JFrame("Add IP");
            textframe3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe3.setVisible(true);
            textframe3.setSize(400,200);

            jp3.add(jt3);
            jt3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = jt3.getText();
                    jl3.setText(input);
                }
            });

            jp3.add(jb);
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = jt3.getText();
                    jl3.setText(input);
                }
            });

            jp3.add(jl3);

            textframe3.add(jp3);
        }
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }
}