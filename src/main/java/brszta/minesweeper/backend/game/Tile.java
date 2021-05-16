package brszta.minesweeper.backend.game;

import java.io.Serializable;

public class Tile implements Serializable {

    private int x;
    private int y;
    private boolean isBomb;
    private boolean isFlagged;
    private boolean isFlipped;
    private int bombsNearby;

    public Tile(int x, int y, boolean isBomb, boolean isFlagged, boolean isFlipped, int bombsNearby) {
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
        this.isFlagged = isFlagged;
        this.isFlipped = isFlipped;
        this.bombsNearby = bombsNearby;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public int getBombsNearby() {
        return bombsNearby;
    }

    public void setBombsNearby(int bombsNearby) {
        this.bombsNearby = bombsNearby;
    }
}