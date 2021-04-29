package brszta.minesweeper.backend;

public class Controller {


    private boolean running;
    private boolean newBoard;

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

    public Controller() {
        this.running = false;
    }
}
