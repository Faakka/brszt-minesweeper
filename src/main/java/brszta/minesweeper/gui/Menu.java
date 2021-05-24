package brszta.minesweeper.gui;

import brszta.minesweeper.backend.Controller;
import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.network.UDPServer;

import brszta.minesweeper.backend.game.Highscores;
import brszta.minesweeper.backend.game.Score;

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
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel3Host = new JPanel();
    private JLabel jLabel3 = new JLabel();
    private JTextField jText3 = new JTextField("", 30);
    private JTextArea jtextareaHost = new JTextArea("Your IP: \n" + justForIp.getOwnIp());
    private JButton jButtom3 = new JButton("Enter");
    private JTextArea jtextarea = new JTextArea(" Balogh Botond\n Parragh Benedek\n Péntek Róbert");
    //private Controller controller;
    //private Game game;

    int delay = 3000;
    public Timer clientWindowDisposeTimer = new Timer( delay, new ActionListener(){
        @Override
        public void actionPerformed( ActionEvent e ){
            textframe3.dispose();
            clientWindowDisposeTimer.stop();
        }
    });
     public Timer hostWindowDisposeTimer = new Timer( delay, new ActionListener(){
        @Override
        public void actionPerformed( ActionEvent e ){
            textframe4.dispose();
            hostWindowDisposeTimer.stop();
        }
    });



    //private Controller controller;
    //private Game game;
    private Score score;

    public Menu(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;
        clientWindowDisposeTimer.setRepeats(false);
        hostWindowDisposeTimer.setRepeats(false);



        menuBar = new JMenuBar();
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

        menu3 = new JMenu("Connection");
        menuBar.add(menu3);
        menu3Item2 = new JMenuItem("Host game");
        menu3Item2.addActionListener(this);
        menu3.add(menu3Item2);

        menu3Item1 = new JMenuItem("Connect to host");
        menu3Item1.addActionListener(this);
        menu3.add(menu3Item1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem1){
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
        if (e.getSource() == checkItem1 && checkItem1.isSelected()){
            game.setLevel(1);
        }

        if (e.getSource() == checkItem2 && checkItem2.isSelected()){
            game.setLevel(2);
        }

        if (e.getSource() == checkItem3 && checkItem3.isSelected()){
            game.setLevel(3);
        }

        if (e.getSource() == menu2Item1){
            textframe2E = new JFrame("Beginner level");
            textframe2E.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe2E.setVisible(true);
            textframe2E.setSize(300,300);

            JTextArea textArea = new JTextArea();
            textArea.append("\n---------------BEGINNER----------------\n");
            textArea.append("--------------HIGHSCORES----------------\n\n");
            int counter = 1;
            if(Highscores.beginnerList.isEmpty()) {
                textArea.append("\tThe list is empty.\n");
            } else {
                for(Score score : Highscores.beginnerList) {
                    textArea.append("\t" + counter + ". " + score.getName() + " - " + score.getFormattedTime() + "\n");
                    counter++;
                }
            }
            textframe2E.getContentPane().add(textArea);
        }

        if (e.getSource() == menu2Item2){
            textframe2M = new JFrame("Advanced level");
            textframe2M.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe2M.setVisible(true);
            textframe2M.setSize(300,300);

            JTextArea textArea = new JTextArea();
            textArea.append("\n----------------ADVANCED----------------\n");
            textArea.append("---------------HIGHSCORES----------------\n\n");
            int counter = 1;
            if(Highscores.advancedList.isEmpty()) {
                textArea.append("\tThe list is empty.\n");
            } else {
                for(Score score : Highscores.advancedList) {
                    textArea.append("\t" + counter + ". " + score.getName() + " - " + score.getFormattedTime() + "\n");
                    counter++;
                }
            }
            textframe2M.getContentPane().add(textArea);
        }

        if (e.getSource() == menu2Item3){
            textframe2H = new JFrame("Expert level");
            textframe2H.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe2H.setVisible(true);
            textframe2H.setSize(300,300);

            JTextArea textArea = new JTextArea();
            textArea.append("\n-----------------EXPERT----------------\n");
            textArea.append("---------------HIGHSCORES----------------\n\n");
            int counter = 1;
            if(Highscores.expertList.isEmpty()) {
                textArea.append("\tThe list is empty.\n");
            } else {
                for(Score score : Highscores.expertList) {
                    textArea.append("\t" + counter + ". " + score.getName() + " - " + score.getFormattedTime() + "\n");
                    counter++;
                }
            }
            textframe2H.getContentPane().add(textArea);
        }

        if (e.getSource() == menu3Item2){ //CONNECTION TO MUNTI PLAYER
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