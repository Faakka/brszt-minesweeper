package brszta.minesweeper.backend.game;

public class Score {

    private String name;
    private int level;
    private int time;

    public Score(String name, int level, int time) {
        this.name = name;
        this.level = level;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getFormattedTime() {
        return (time/60) + "m " + (time%60) + "s";
    }

}
