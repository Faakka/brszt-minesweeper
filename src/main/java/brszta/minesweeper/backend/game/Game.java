package brszta.minesweeper.backend.game;

import java.io.Serializable;

public class Game implements Serializable {

    private String type;
    private int level;
    private Board board;
    private long startTime;
    private int gameTime;
    private int gameStat = 0;
    private boolean youWon = false;
    private boolean gameOver = false;

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isYouWon() {
        return youWon;
    }

    public void setYouWon(boolean youWon) {
        this.youWon = youWon;
    }

    public int getGameStat() {
        return gameStat;
    }

    public void setGameStat(int gameStat) {
        this.gameStat = gameStat;
    }

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
