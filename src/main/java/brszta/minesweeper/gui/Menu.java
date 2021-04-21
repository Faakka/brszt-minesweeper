package brszta.minesweeper.gui;

import javax.swing.*;
import java.awt.event.*;

public class Menu implements ActionListener{

    private JMenuBar menuBar;
    private JMenu menu, menu2;
    private JMenu subMenu;
    private JMenuItem menuItem1, menuItem2, menuItem3;
    private JCheckBoxMenuItem checkItem1, checkItem2, checkItem3;

    private JFrame textframe;
    private JPanel jp = new JPanel();
    private JLabel jl = new JLabel();
    private JTextField jt = new JTextField("Default", 30);
    private JButton jb = new JButton("Enter");
    private JTextArea jm = new JTextArea("Developers: Balogh Botond, Parragh Benedek, Péntek Róbert");

    public Menu() {

        menuBar = new JMenuBar();

        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);

        menu2 = new JMenu("Edit");
        menu2.setMnemonic(KeyEvent.VK_E);
        menuBar.add(menu2);

        menuItem1 = new JMenuItem("MAP");
        menuItem1.setMnemonic(KeyEvent.VK_L);
        menuItem1.addActionListener(this);
        menu.add(menuItem1);

        menuItem2 = new JMenuItem("Save");
        menuItem2.setMnemonic(KeyEvent.VK_S);
        menuItem2.addActionListener(this);
        menu.add(menuItem2);

        menuItem3 = new JMenuItem("Option");
        menuItem3.setMnemonic(KeyEvent.VK_O);
        menuItem3.addActionListener(this);
        menu.add(menuItem3);

        subMenu =new JMenu("Level");
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem1){
            System.out.println("visit set");
        }
        if (e.getSource() == menuItem2){
            System.out.println("You have saved the file");

            textframe = new JFrame("Text");
            textframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textframe.setVisible(true);
            textframe.setSize(400,200);

            jp.add(jt);
            jt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = jt.getText();
                    jl.setText(input);
                }
            });

            jp.add(jb);
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = jt.getText();
                    jl.setText(input);
                }
            });

            jp.add(jl);

            jp.add(jm);
            jm.addNotify();
            textframe.add(jp);
        }
        if (e.getSource() == menuItem3){
            System.out.println("You have opened the option menu");
        }
        if (e.getSource() == checkItem1 && checkItem1.isSelected()){
            System.out.println("You have selected Easy level: 9x9");

        }

        if (e.getSource() == checkItem2 && checkItem2.isSelected()){
            System.out.println("You have selected Medium level: 9x16");
        }

        if (e.getSource() == checkItem3 && checkItem3.isSelected()){
            System.out.println("You have selected Hard level: 11x21");
        }
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }
}