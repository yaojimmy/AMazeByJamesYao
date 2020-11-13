package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.wm.cs.cs301.jamesyao.generation.Factory;
import edu.wm.cs.cs301.jamesyao.generation.Floorplan;
import edu.wm.cs.cs301.jamesyao.generation.Maze;
import edu.wm.cs.cs301.jamesyao.generation.MazeBuilderEller;
import edu.wm.cs.cs301.jamesyao.generation.MazeFactory;

import edu.wm.cs.cs301.jamesyao.R;
import edu.wm.cs.cs301.jamesyao.generation.Order;

public class GeneratingActivity extends AppCompatActivity implements Order {

    public static final String MANUAL_MESSAGE = "edu.wm.cs.cs301.jamesyao.MANUAL";
    public static final String ANIMATION_MESSAGE = "edu.wm.cs.cs301.jamesyao.ANIMATION";
    public static final String TAG = "CodeRunner";

    // factory used to make the maze
    protected Factory factory;

    // about the maze and its generation
    private int seed; // the seed value used for the random number generator
    private int skillLevel; // user selected skill level, i.e. size of maze
    private Order.Builder builder; // selected maze generation algorithm
    private boolean perfect; // selected type of maze, i.e.
    // perfect == true: no loops, i.e. no rooms
    // perfect == false: maze can support rooms

    // percent done
    private int percentdone;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);

        // Get the Intent that started this activity and extract the extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // get the values selected by users in the title screen
        skillLevel = extras.getInt(AMazeActivity.DIFFICULTY);
        Resources res = getResources();
        String maze_type = extras.getString(AMazeActivity.MAZE_TYPE);
        // String[] gen_algs = res.getStringArray(R.array.select_generation_alg);
        switch (maze_type) {
            case "Eller":
                builder = Order.Builder.Eller;
            case "Prim":
                builder = Order.Builder.Prim;
            case "DFS":
                builder = Order.Builder.DFS;
        }
        perfect = !extras.getBoolean(AMazeActivity.ROOMS);

        // arbitrary fixed value for seed; will create method to pick random seed value later
        seed = 13;

        // reset percentage for progress
        percentdone = 0;

        // Capture the layout's TextView and set the string as its text (for now)
        TextView textView = findViewById(R.id.textView11);
        String message = "Difficulty: " + skillLevel + " Maze Type: " + maze_type + " Rooms: " + !perfect;
        textView.setText(message);

        updateProgressBar();
        /*
         * Robot and driver declaration, which are initialized with their respective spinner values when this activity ends
         * Maze generation goes here
         */

    }

    /** Called when the user taps the "Play Manually" button
     * Goes to PlayManuallyActivity */
    public void play_manually(View view) {
        Intent intent = new Intent(this, PlayManuallyActivity.class);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        intent.putExtra(MANUAL_MESSAGE, progressBar.getProgress());
        startActivity(intent);
    }

    /** Called when the user taps the "Play Animation" button
     * Goes to PlayAnimationActivity */
    public void play_animation(View view) {
        Intent intent = new Intent(this, PlayAnimationActivity.class);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        intent.putExtra(ANIMATION_MESSAGE, progressBar.getProgress());
        startActivity(intent);
    }

    public void updateProgressBar() {
        new Thread(new Runnable() {
            public void run() {
                factory = new MazeFactory();
                factory.order(dummy());
            }
        }).start();
    }

    @Override
    public int getSkillLevel() {
        return skillLevel;
    }

    @Override
    public Builder getBuilder() {
        return builder;
    }

    @Override
    public boolean isPerfect() {
        return perfect;
    }

    @Override
    public int getSeed() {
        return seed;
    }

    public int getPercentDone() {
        return percentdone;
    }

    @Override
    public void deliver(Maze mazeConfig) {

    }

    @Override
    public void updateProgress(int percentage) {
        if (this.percentdone < percentage && percentage <= 100) {
            this.percentdone = percentage;
            final ProgressBar progressBar = findViewById(R.id.progressBar);
            //current value in the text view
            handler.post(new Runnable() {
                public void run() {
                    progressBar.setProgress(percentdone);
                }
            });
        }
    }

    public GeneratingActivity dummy() {
        return this;
    }
}
