package brszta.minesweeper.gui;

import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.backend.game.Highscores;
import brszta.minesweeper.backend.game.Score;
import brszta.minesweeper.backend.io.JsonIO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class InsertData extends JFrame{

    private final JLabel jLabelWinbox = new JLabel();
    private final JTextField jTextWinbox = new JTextField("Your name", 30);

    private Score score;

    public InsertData(Score score) {
        this.score = score;

        JFrame jFrameWinbox = new JFrame("You win");
        jFrameWinbox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrameWinbox.setVisible(true);
        jFrameWinbox.setSize(400,200);
        JPanel jPanelWinbox = new JPanel();

        jPanelWinbox.add(jTextWinbox);
        jTextWinbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jTextWinbox.getText();
                jLabelWinbox.setText(name);
            }
        });

        JButton jButtomWinbox = new JButton("Save");
        jPanelWinbox.add(jButtomWinbox);
        jButtomWinbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jTextWinbox.getText();
                jLabelWinbox.setText(name);
                score.setName(name);
                Highscores.appendScore(score);
                Highscores.sortScores(score.getLevel());
                JsonIO.writeScores(score.getLevel());
            }
        });
        jPanelWinbox.add(jLabelWinbox);
        jFrameWinbox.add(jPanelWinbox);
    }
}
