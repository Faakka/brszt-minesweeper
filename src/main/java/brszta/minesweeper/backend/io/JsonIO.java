package brszta.minesweeper.backend.io;

import brszta.minesweeper.backend.game.Score;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class JsonIO {

    final private String prefix = "src/main/resources/";
    final private String file1 = "beginner.json";
    final private String file2 = "advanced.json";
    final private String file3 = "expert.json";

    public void writeScores(ArrayList<Score> scores, int level) {   
        String path = "";
        if(level == 1)
            path = prefix + file1;
        else if(level == 2)
            path = prefix + file2;
        else
            path = prefix + file3;

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

    public void readScores(ArrayList<Score> scores, int level) {
        String path = "";

        if(level == 1)
            path = prefix + file1;
        else if(level == 2)
            path = prefix + file2;
        else
            path = prefix + file3;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Score[] scoreArray = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            scoreArray = gson.fromJson(br, Score[].class);

            for(Score score : scoreArray) {
                scores.add(score);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
