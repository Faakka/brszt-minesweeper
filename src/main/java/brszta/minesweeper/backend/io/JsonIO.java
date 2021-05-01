package brszta.minesweeper.backend.io;

import brszta.minesweeper.backend.game.Highscores;
import brszta.minesweeper.backend.game.Score;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class JsonIO {

    final private static String prefix = "src/main/resources/";
    final private static String file1 = "beginner.json";
    final private static String file2 = "advanced.json";
    final private static String file3 = "expert.json";

    public static void writeScores(int level) {
        String path = "";
        ArrayList<Score> scores;
        if(level == 1) {
            path = prefix + file1;
            scores = Highscores.beginnerList;
        }
        else if(level == 2) {
            path = prefix + file2;
            scores = Highscores.advancedList;
        }
        else {
            path = prefix + file3;
            scores = Highscores.expertList;
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            FileWriter writer = new FileWriter(path);
            writer.write(gson.toJson(scores));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readScores(int level) {
        String path = "";
        ArrayList<Score> scores;
        if(level == 1) {
            path = prefix + file1;
            scores = Highscores.beginnerList;
        }
        else if(level == 2) {
            path = prefix + file2;
            scores = Highscores.advancedList;
        }
        else {
            path = prefix + file3;
            scores = Highscores.expertList;
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Score[] scoreArray = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            scoreArray = gson.fromJson(br, Score[].class);

            if(scoreArray != null)
                for(Score score : scoreArray) {
                    scores.add(score);
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
