package brszta.minesweeper.backend.game;

import java.util.ArrayList;
import java.util.Comparator;

public class Highscores {

    private ArrayList<Score> beginnerList = new ArrayList<>();
    private ArrayList<Score> advancedList = new ArrayList<>();
    private ArrayList<Score> expertList = new ArrayList<>();

    public ArrayList<Score> getList(int level) {
        if(level == 1)
            return beginnerList;
        else if(level == 2)
            return advancedList;
        else if(level == 3)
            return advancedList;

        return null;
    }

    public void appendScore(Score score) {
        if(score.getLevel() == 1)
            beginnerList.add(score);
        else if(score.getLevel() == 2)
            advancedList.add(score);
        else if(score.getLevel() == 3)
            expertList.add(score);
    }

    public void sortScores(int level) {
        ArrayList<Score> scores = null;
        if(level == 1)
            scores = beginnerList;
        else if(level == 2)
            scores = advancedList;
        else
            scores = expertList;

        scores.sort(new Comparator<Score>() {
            @Override
            public int compare(Score score1, Score score2) {
                return score1.getTime() > score2.getTime() ? 1 : (score1.getTime() < score2.getTime() ? -1 : 0);
            }
        });
    }

    public void formattedPrint(int level) {
        ArrayList<Score> scores = null;
        if(level == 1)
            scores = beginnerList;
        else if(level == 2)
            scores = advancedList;
        else
            scores = expertList;

        int counter = 1;
        System.out.println("\n----------------BEGINNER----------------");
        System.out.println("---------------HIGHSCORES----------------\n");
        if(scores.isEmpty()) {
            System.out.println("\t\t\tThe list is empty.\n");
        } else {
            for(Score score : scores) {
                System.out.println("\t\t\t" + counter + ". " + score.getName() + " - " + score.getFormattedTime() + "\n");
                counter++;
            }
        }
    }
}
