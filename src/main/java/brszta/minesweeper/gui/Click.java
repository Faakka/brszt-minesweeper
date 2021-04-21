package brszta.minesweeper.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

public class Click implements MouseListener {

    private int clickedX;
    private int clickedY;
    private boolean isLeftClick = false;
    private boolean newClick = false;

    @Override
    public void mouseClicked(MouseEvent e) {

        this.clickedY = e.getX();
        this.clickedX = e.getY();
        this.newClick = true;

        if(SwingUtilities.isLeftMouseButton(e)) {
            isLeftClick = true;
        } else if(SwingUtilities.isRightMouseButton(e)) {
            isLeftClick = false;
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

    public int getClickedX() {
        return clickedX;
    }

    public int getClickedY() {
        return clickedY;
    }

    public boolean isLeftClick() {
        return isLeftClick;
    }

    public boolean isNewClick() {
        return newClick;
    }

    public void setNewClick(boolean newClick) {
        this.newClick = newClick;
    }
}
