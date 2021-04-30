package brszta.minesweeper;

import brszta.minesweeper.backend.Controller;
import brszta.minesweeper.backend.game.Game;
import brszta.minesweeper.gui.Display;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;



public class Seconds {
    Timer timer;
    long time;
    long startTime;
    Controller controller;
    Game game;
    Display display;

    private int timeX = 400;
    private int timeY = 6;

    public void stopTimer() {
        timer.cancel();
    }

    public Seconds(Controller controller, Game game, Display display, long startTime) {
        timer = new Timer();
        timer.schedule(new SecondsTask(), 100, 1000);
        this.startTime = startTime;
        this.controller = controller;
        this.game = game;
        this.display = display;
    }

    class SecondsTask extends TimerTask {
        @Override
        public void run() {
            if (controller.isRunning()) {
                time = ((System.currentTimeMillis() - startTime) / 1000);
                game.setGameTime(time);
                //System.out.println(game.getGameTime());
                display.repaint();
            } else {
                timer.cancel();
                //System.out.println("Fut a timer cancel");
            }
        }
    }


}
