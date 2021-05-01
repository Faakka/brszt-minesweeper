package brszta.minesweeper.gui;

import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.backend.game.Score;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class InsertData extends JFrame{

    private JFrame jFrameWinbox;
    private JPanel jPanelWinbox = new JPanel();
    private JLabel jLabelWinbox = new JLabel();
    private JTextField jTextWinbox = new JTextField("Your name", 30);
    private JTextField jTexttime = new JTextField("Your time", 30);
    private JButton jButtomWinbox = new JButton("Save");

    private Game game;
    private Score score;

    public InsertData(Game game) {

        jFrameWinbox= new JFrame("You win");
        jFrameWinbox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrameWinbox.setVisible(true);
        jFrameWinbox.setSize(400,200);

        jPanelWinbox.add(jTextWinbox);
        jTextWinbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jTextWinbox.getText();
                jLabelWinbox.setText(name);
            }
        });

        jPanelWinbox.add(jButtomWinbox);
        jButtomWinbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jTextWinbox.getText();
                //String time = game.getGameTime();
                jLabelWinbox.setText(name);

/*
                try{
                    BufferedWriter bwc = new BufferedWriter(new FileWriter("winners-copy.txt"));
                    BufferedReader br = new BufferedReader(new FileReader("winners.txt"));
                    String earlier;

                    while((earlier = br.readLine()) != null) { bwc.write( earlier + "\n"); }//+TIME
                    bwc.close();
                    br.close();

                    BufferedWriter bw = new BufferedWriter(new FileWriter("winners.txt"));
                    BufferedReader brc = new BufferedReader(new FileReader("winners-copy.txt"));
                    String copy;
                    while((copy = brc.readLine()) != null) { bw.write(copy + "\n"); }//+TIME
                    bw.write(""+name);//+TIME

                    brc.close();
                    bw.close();
                } catch (Exception f){
                    return;
                }*/
            }
        });
        jPanelWinbox.add(jLabelWinbox);
        jFrameWinbox.add(jPanelWinbox);
    }
}
