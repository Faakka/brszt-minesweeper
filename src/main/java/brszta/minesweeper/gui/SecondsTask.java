package brszta.minesweeper.gui;

import brszta.minesweeper.gui.Controller;
import brszta.minesweeper.gui.Display;

import java.util.Timer;
import java.util.TimerTask;

public class SecondsTask extends TimerTask {

    Timer timer;
    Controller controller;
    Display display;

    public SecondsTask(Controller controller, Display display) {
        this.timer = new Timer();
        this.display = display;
        this.controller = controller;
        this.timer.schedule(this, 100, 1000);
    }

    @Override
    public void run() {
        if (controller.isRunning()) {
            display.getGame().calcGameTime();
            display.repaint();
        } else {
            timer.cancel();
        }
    }

}
