package brszta.minesweeper.backend.game;

import brszta.minesweeper.gui.GUI;

import java.util.Random;

public class Board {

    private int width;
    private int height;
    private Tile[][] board;
    private int numOfBombs;
    private int numOfFlags;
    private int progress = 0;

    public Board(int level) {
        if(level == 1) {
            this.width = 9;
            this.height = 9;
            this.board = new Tile[9][9];
            this.numOfBombs = 10;
            this.numOfFlags = 10;
        }
        if(level == 2) {
            this.width = 16;
            this.height = 16;
            this.board = new Tile[16][16];
            this.numOfBombs = 40;
            this.numOfFlags = 40;
        }
        if(level == 3) {
            this.width = 30;
            this.height = 16;
            this.board = new Tile[16][30];
            this.numOfBombs = 99;
            this.numOfFlags = 99;
        }
    }

    public void generate() {
        setupBoard();
        placeBombs();
        fillSpaces();
    }

    private void setupBoard() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                this.board[i][j] = new Tile(j, i, false, false, false, 0);
            }
        }
    }

    private void placeBombs() {
        Random random = new Random();

        int counter = 0;
        int x, y;

        while(counter != this.numOfBombs) {
            x = random.nextInt(height - 1);
            y = random.nextInt(width - 1);
            if(!this.board[x][y].isBomb()) {
                this.board[x][y].setBomb(true);
                counter++;
            }
        }
    }

    private void fillSpaces() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(!board[i][j].isBomb()) {
                    board[i][j].setBombsNearby(numOfBombsNearby(i, j));
                }
            }
        }
    }

    private int numOfBombsNearby(int x, int y) {
        int bombCounter = 0;

        if(x > 0 && board[x-1][y].isBomb())
            bombCounter++;
        if(x < height-1 && board[x+1][y].isBomb())
            bombCounter++;
        if(y > 0 && board[x][y-1].isBomb())
            bombCounter++;
        if(y < width-1 && board[x][y+1].isBomb())
            bombCounter++;
        if(x > 0 && y > 0 && board[x-1][y-1].isBomb())
            bombCounter++;
        if(x > 0 && y < width-1 && board[x-1][y+1].isBomb())
            bombCounter++;
        if(x < height-1 && y > 0 && board[x+1][y-1].isBomb())
            bombCounter++;
        if(x < height-1 && y < width-1 && board[x+1][y+1].isBomb())
            bombCounter++;

        return bombCounter;
    }

    public void flagTile(int x, int y) {
        if(x >= 0 && x < height && y >= 0 && y < width) {
            if (board[x][y].isFlagged()) {
                board[x][y].setFlagged(false);
                numOfFlags++;
            } else if (!board[x][y].isFlipped() && !board[x][y].isFlagged() && numOfFlags != 0) {
                board[x][y].setFlagged(true);
                numOfFlags--;
            }
        }
    }

    public int flipTile(int x, int y) {
        if(x >= 0 && x < height && y >= 0 && y < width) {
            if (!board[x][y].isFlipped() && !board[x][y].isFlagged()) {
                if (board[x][y].isBomb()) {
                    flipBombs();
                    return 1;
                } else {
                    board[x][y].setFlipped(true);
                    if (board[x][y].getBombsNearby() == 0)
                        flipEmpties(x, y);
                    progress++;
                }
            }

            if (progress == (width * height - numOfBombs))
                return 2;
        }
        return 0;
    }

    private void flipBombs() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(board[i][j].isBomb())
                    board[i][j].setFlipped(true);
            }
        }
    }

    private void flipEmpties(int x, int y) {

        if(x > 0 && !board[x-1][y].isFlipped() && !board[x-1][y].isFlagged()) {
            flipTile(x-1, y);
        }
        if(x < height-1 && !board[x+1][y].isFlipped() && !board[x+1][y].isFlagged()) {
            flipTile(x+1, y);
        }
        if(y > 0 && !board[x][y-1].isFlipped() && !board[x][y-1].isFlagged()) {
            flipTile(x, y-1);
        }
        if(y < width-1 && !board[x][y+1].isFlipped() && !board[x][y+1].isFlagged()) {
            flipTile(x, y+1);
        }
        if(x > 0 && y > 0 && !board[x-1][y-1].isFlipped() && !board[x-1][y-1].isFlagged()) {
            flipTile(x-1, y-1);
        }
        if(x > 0 && y < width-1 && !board[x-1][y+1].isFlipped() && !board[x-1][y+1].isFlagged()) {
            flipTile(x-1, y+1);
        }
        if(x < height-1 && y > 0 && !board[x+1][y-1].isFlipped() && !board[x+1][y-1].isFlagged()) {
            flipTile(x+1, y-1);
        }
        if(x < height-1 && y < width-1 && !board[x+1][y+1].isFlipped() && !board[x+1][y+1].isFlagged()) {
            flipTile(x+1, y+1);
        }
    }

    public int pixelToX(int pixelX, int pixelY) {
        for(int i = 0; i < height; i++ ){
            for(int j = 0; j < width; j++){
                if(((pixelX >= (GUI.SPACING+(i*GUI.TILE_SIZE)+GUI.X_MARGIN)) &&
                        ((pixelX < ((i*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.X_MARGIN)-GUI.SPACING))) &&
                        ((pixelY >= (GUI.SPACING+((j*GUI.TILE_SIZE)+GUI.Y_MARGIN))) &&
                         (pixelY < (((j*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.Y_MARGIN)-GUI.SPACING)))) {
                    return i;
                }
            }
        }
        return (-1);
    }

    public int pixelToY(int pixelX, int pixelY) {
        for(int i = 0; i < height; i++ ){
            for(int j = 0; j < width; j++){
                if(((pixelX >= (GUI.SPACING+(i*GUI.TILE_SIZE)+GUI.X_MARGIN)) &&
                        ((pixelX < ((i*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.X_MARGIN)-GUI.SPACING))) &&
                        ((pixelY >= (GUI.SPACING+((j*GUI.TILE_SIZE)+GUI.Y_MARGIN))) &&
                         (pixelY < (((j*GUI.TILE_SIZE)+GUI.TILE_SIZE+GUI.Y_MARGIN)-GUI.SPACING)))) {
                    return j;
                }
            }
        }
        return (-1);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int Height) {
        this.height = height;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public int getNumOfBombs() {
        return numOfBombs;
    }

    public void setNumOfBombs(int numOfBombs) {
        this.numOfBombs = numOfBombs;
    }

    public int getNumOfFlags() {
        return numOfFlags;
    }

    public void setNumOfFlags(int numOfFlags) {
        this.numOfFlags = numOfFlags;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
