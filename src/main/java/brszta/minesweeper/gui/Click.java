package brszta.minesweeper.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Click implements MouseListener {

    private final GUI gui;

    public Click(GUI gui) {
        this.gui = gui;
    }

    //Mouse click event handler-------------------------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {

        /*
        if (inBoxX() != -1 && inBoxY() != -1){
            revealed[inBoxX()][inBoxY()] = true; // beállítja mouse alatti kocka láthatóságát
        }
        */
        //egér pozicióját menti két globális változóba
        gui.mx = e.getX();
        gui.my = e.getY();

        // Plus Minus gomb viszgálat
        if ((gui.mx >= gui.minusX + 20) && (gui.mx < gui.minusX + 60) && (gui.my >= gui.minusY + 40) && (gui.my < gui.minusY + 80)) {
            gui.spacing--;
            if (gui.spacing < 1) {
                gui.spacing = 1;
            }
        } else if ((gui.mx >= gui.plusX + 20) && (gui.mx < gui.plusX + 60) && (gui.my >= gui.plusY + 40) && (gui.my < gui.plusY + 80)) {
            gui.spacing++;
            if (gui.spacing > 15) {
                gui.spacing = 15;
            }
        }


        // Flagging the cells
        if ((gui.inBoxX() != -1) && (gui.inBoxY() != -1)) {
            if (gui.flagger == true && gui.revealed[gui.inBoxX()][gui.inBoxY()] == false) {
                if (gui.flagged[gui.inBoxX()][gui.inBoxY()] == false) {
                    gui.flagged[gui.inBoxX()][gui.inBoxY()] = true;
                } else {
                    gui.flagged[gui.inBoxX()][gui.inBoxY()] = false;
                }
            } else {
                if (gui.flagged[gui.inBoxX()][gui.inBoxY()] == false) {
                    gui.revealed[gui.inBoxX()][gui.inBoxY()] = true; // beállítja mouse alatti kocka láthatóságát ha nincsen flaggelve
                }
            }
        } else {
            System.out.println("The pointer is not inside in any box");
        }

/*/         /Csak kiiratás hogy hol klikkelsz---------------------------------------------------------------------------
        if(inBoxX() != -1 && inBoxY() != -1){
            System.out.println("The mouse is in the [" + inBoxX() + ", " + inBoxY() + "], Number of neighs: " + neighbours[inBoxX()][inBoxY()]);
        }
        else{
            System.out.println("The pinter is not inside the box!");
        }
*/
        if (gui.inSmiley() == true) {
            //System.out.println("The pointer is inside the smiley!");
            gui.resetAll();
        }

        if (gui.inFlagger() == true) {
            //System.out.println("The pointer is inside the Flagger!");
            if (gui.flagger == false) {
                gui.flagger = true;
            } else {
                gui.flagger = false;
                System.out.println("The pointer is not the Flagger!");
            }
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
