package brszta.minesweeper.backend;

import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.gui.Click;
import brszta.minesweeper.gui.Display;

public class Controller {

    private boolean running;
    private boolean newBoard;
    private boolean multiplayer;
    private boolean host;
    private boolean connected;
    private String ipToConnect;
    private boolean newGame = false;


    public boolean isNewGame() {
        return newGame;
    }

    public void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }

    public String getIpToConnect() {
        return ipToConnect;
    }

    public void setIpToConnect(String ipToConnect) {
        this.ipToConnect = ipToConnect;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

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

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public int playGame(Game game, Display display, Click click) {
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

        display.repaint();

        if(response == 1) {
            this.setRunning(false);
            game.setGameStat(1);
            game.setGameOver(true);
            return 1;
        } else if(response == 2){
            this.setRunning(false);
            game.setGameStat(2);
            game.setGameOver(false);
            game.setYouWon(true);
            return 2;
        }
        return 0;
    }

    public void sleepInMs(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
