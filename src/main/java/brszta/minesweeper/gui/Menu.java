package brszta.minesweeper.gui;

import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.backend.game.Highscores;
import brszta.minesweeper.network.UDPClient;
import brszta.minesweeper.network.UDPServer;


import javax.swing.*;
import java.awt.event.*;


public class Menu implements ActionListener{
    private Game game;
    private Controller controller;
    private UDPServer justForIp = new UDPServer();



    private JMenuBar menuBar;
    private JMenu menu, menu2, menu3;
    private JMenu subMenu;
    private JMenuItem menuItem1, menuItem2, menu2Item1, menu2Item2,menu2Item3, menu3Item1, menu3Item2;
    private JRadioButton checkItem1, checkItem2, checkItem3;

    private JFrame textframe, textframe2E, textframe2M, textframe2H, textframe3, textframe4;
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2E = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel3Host = new JPanel();
    private JLabel jLabel3Host = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JTextField jText3 = new JTextField("", 30);
    private JTextArea jtextareaHost = new JTextArea("Your IP: \n" + justForIp.getOwnIp());
    private JButton jButtom3 = new JButton("Enter");
    private JTextArea jtextarea = new JTextArea(" Balogh Botond\n Parragh Benedek\n Péntek Róbert");



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

        ButtonGroup group = new ButtonGroup();

        subMenu =new JMenu("Level option");
        menu.add(subMenu);

        checkItem1 = new JRadioButton("Beginner");
        checkItem1.addActionListener(this);
        subMenu.add(checkItem1);
        group.add(checkItem1);

        checkItem2 = new JRadioButton("Advanced");
        checkItem2.addActionListener(this);
        subMenu.add(checkItem2);
        group.add(checkItem2);

        checkItem3 = new JRadioButton("Expert");
        checkItem3.addActionListener(this);
        subMenu.add(checkItem3);
        group.add(checkItem3);

        menuItem2 = new JMenuItem("Creators");
        menuItem2.addActionListener(this);
        menu.add(menuItem2);
        //-------GAME MENU END

        //-------RES MENU BEGIN
        menu2 = new JMenu("Results");
        menuBar.add(menu2);
        menu2Item1 = new JMenuItem("Beginner");
        menu2Item1.addActionListener(this);
        menu2.add(menu2Item1);
        menu2Item2 = new JMenuItem("Advanced");
        menu2Item2.addActionListener(this);
        menu2.add(menu2Item2);
        menu2Item3 = new JMenuItem("Expert");
        menu2Item3.addActionListener(this);
        menu2.add(menu2Item3);
        //-------RES MENU END

        //-------CON. MENU BEGIN
        menu3 = new JMenu("Connection");
        menuBar.add(menu3);
        menu3Item2 = new JMenuItem("Host game");
        menu3Item2.addActionListener(this);
        menu3.add(menu3Item2);

        menu3Item1 = new JMenuItem("Connect to host");
        menu3Item1.addActionListener(this);
        menu3.add(menu3Item1);
        //-------CON. MENU END
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem1){
            System.out.println("New game generated");
            controller.setMultiplayer(false);
            controller.setRunning(false);
            controller.setNewBoard(true);
        }
        if (e.getSource() == menuItem2){
            textframe = new JFrame("Developers");
            textframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe.setVisible(true);
            textframe.setSize(180,80);

            jPanel1.add(jtextarea);
            jtextarea.addNotify();
            textframe.add(jPanel1);
        }
        if (e.getSource() == checkItem1 && checkItem1.isSelected()){ //EASY
            System.out.println("You have selected Beginner level: 9x9");
            game.setLevel(1);
        }

        if (e.getSource() == checkItem2 && checkItem2.isSelected()){ //MEDIUM
            System.out.println("You have selected Advanced level: 16x16");
            game.setLevel(2);
        }

        if (e.getSource() == checkItem3 && checkItem3.isSelected()){ //HARD
            System.out.println("You have selected Expert level: 16x30");
            game.setLevel(3);
        }

        if (e.getSource() == menu2Item1){
            System.out.println("Easy level results");
            textframe2E = new JFrame("Beginner level");
            textframe2E.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe2E.setVisible(true);
            textframe2E.setSize(400,400);
            // Highscores.formattedPrint(1);
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

        if (e.getSource() == menu3Item2){ //CONNECTION TO MULTI PLAYER
            System.out.println("Host started. Waiting for connection");
            textframe4 = new JFrame("Waiting to other players...");
            textframe4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe4.setVisible(true);
            textframe4.setSize(400,200);


            jPanel3Host.add(jtextareaHost);
            jtextareaHost.addNotify();
            textframe4.add(jPanel3Host);

            controller.setMultiplayer(true);
            controller.setHost(true);


        }

        if (e.getSource() == menu3Item1){
            System.out.println("Connect to host");
            textframe3 = new JFrame("Add IP");
            textframe3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe3.setVisible(true);
            textframe3.setSize(400,200);

            jPanel3.add(jText3);
            jText3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = jText3.getText();
                    jLabel3.setText(input);

                }
            });

            jPanel3.add(jButtom3);
            jButtom3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = jText3.getText();
                    jLabel3.setText(input);
                    controller.setIpToConnect(input);
                    controller.setMultiplayer(true);
                    controller.setHost(false);
                    if(controller.isConnected()) {
                        textframe3.dispose();
                    }
                }
            });

            jPanel3.add(jLabel3);

            textframe3.add(jPanel3);

        }
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }
}