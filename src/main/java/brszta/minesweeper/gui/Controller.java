package brszta.minesweeper.gui;

import brszta.minesweeper.backend.game.Game;

public class Controller {


    private boolean running;
    private boolean newBoard;

    public Controller() {
        this.running = false;
    }

    public boolean isNewBoard() {
        return newBoard;
    }

    public void setNewBoard(boolean newBoard) {
        this.newBoard = newBoard;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void playGame(Game game, Display display, Click click) {
        display.repaint();
        while(!click.isNewClick() && this.running) {
            this.sleepInMs(50);
        }
        click.setNewClick(false);

        int x = game.getBoard().pixelToX(click.getClickedX(), click.getClickedY());
        int y = game.getBoard().pixelToY(click.getClickedX(), click.getClickedY());

        int response = 0;
        if(click.isLeftClick())
            response = game.getBoard().flipTile(x, y);
        else
            game.getBoard().flagTile(x, y);

        if(response == 1) {
            System.out.println("GAME OVER");
            this.setRunning(false);
        } else if(response == 2){
            System.out.println("YOU WIN");
            this.setRunning(false);
        }

        display.repaint();
    }

    public void sleepInMs(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
