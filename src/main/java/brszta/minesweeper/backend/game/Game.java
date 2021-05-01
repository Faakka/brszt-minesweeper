package brszta.minesweeper.backend.game;

public class Game {

    private String type;
    private int level;
    private Board board;
    private long startTime;
    private int gameTime;

    public void calcGameTime() {
        this.gameTime =  (int) ((System.currentTimeMillis() - startTime)/1000);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public String formattedGameTime() {
        int seconds = gameTime % 60;
        int minutes = gameTime / 60;

        String secString  = (seconds < 10) ? ("0" + seconds) : "" + seconds;
        String minString  = (minutes < 10) ? ("0" + minutes) : "" + minutes;

        return minString + ":" + secString;
    }
}
