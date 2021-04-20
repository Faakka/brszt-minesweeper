package brszta.minesweeper.backend.test;

import brszta.minesweeper.backend.game.Board;

import java.util.Scanner;

public class TestAction {

    public static void drawBoard(Board board) {
        for(int i = 0; i < board.getHeight(); i++) {
            for(int j = 0; j < board.getWidth(); j++) {
                if(board.getBoard()[i][j].isFlagged()) {
                    System.out.print("ß");
                }
                else if(board.getBoard()[i][j].isFlipped()) {
                    if(board.getBoard()[i][j].isBomb()) {
                        System.out.print("X");
                    } else if(board.getBoard()[i][j].getBombsNearby() == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print(board.getBoard()[i][j].getBombsNearby());
                    }
                } else {
                    System.out.print("#");
                }

                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void printStat(Board board) {
        System.out.println("\nFLAGS LEFT: " + board.getNumOfFlags());

        int progress = board.getProgress();
        int numOfNotBombs = board.getWidth()*board.getHeight()-board.getNumOfBombs();
        int percentage = 100 * progress / numOfNotBombs;
        System.out.println("PROGRESS: " + progress + "/" + numOfNotBombs + " (" + percentage + "%)");
    }

    public static int pickTile(Board board) {

        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        int intent;

        System.out.print("x: ");
        x = scanner.nextInt();

        System.out.print("y: ");
        y = scanner.nextInt();

        System.out.println("Flip or Flag? (0/1)");
        intent = scanner.nextInt();
        if(x > 0 && x <= board.getHeight() && y > 0 && y <= board.getWidth()) {
            if(intent == 0) {
                return board.flipTile(x-1, y-1);

            } else {
                board.flagTile(x-1, y-1);
                return 0;
            }
        } else {
            System.out.println("The coordinates must be in bounds!");
            System.out.println("x: [1:" + board.getHeight() + "], y: [1:" + board.getWidth() + "]");
            return 0;
        }
    }
}
