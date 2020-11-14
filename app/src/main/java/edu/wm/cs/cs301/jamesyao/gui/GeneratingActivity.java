package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
    public static final String DRIVER = "edu.wm.cs.cs301.jamesyao.DRIVER";
    public static final String ROBOT = "edu.wm.cs.cs301.jamesyao.ROBOT";
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

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);

        // Get the Intent that started this activity and extract the extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // get the values selected by users in the title screen
        skillLevel = extras.getInt(AMazeActivity.DIFFICULTY);
        //Resources res = getResources();
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

        factory = new MazeFactory();
        updateProgressBar();
        factory.waitTillDelivered();
        /*
         * Robot and driver declaration, which are initialized with their respective spinner values when this activity ends
         * Maze generation goes here
         */


        /*
        final Spinner driver = findViewById(R.id.spinner);
        final Spinner robot = findViewById(R.id.spinner3);

        while(percentdone != 100 && driver.getSelectedItem().toString().equals("Select")) {
            Log.v("Not done: ", "Not done");
        }
        if (driver.getSelectedItem().toString().equals("You")) {
            Intent intent_send = new Intent(this, PlayManuallyActivity.class);
            Bundle extras_send = new Bundle();
            extras_send.putString(DRIVER, driver.getSelectedItem().toString());
            extras_send.putString(ROBOT, "None");
            intent_send.putExtras(extras_send);
            startActivity(intent_send);
        }
        else {
            Intent intent_send = new Intent(this, PlayAnimationActivity.class);
            Bundle extras_send = new Bundle();
            extras_send.putString(DRIVER, driver.getSelectedItem().toString());
            extras_send.putString(ROBOT, robot.getSelectedItem().toString());
            intent_send.putExtras(extras_send);
            startActivity(intent_send);
        }*/

        /*
        driver.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Resources res = getResources();
                String[] drivers = res.getStringArray(R.array.driver_selection);
                if (drivers[position].equals("You")) {
                    // starts PlayManually if maze finished generating
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    /** Called when the user taps the "Play Manually" button
     * Goes to PlayManuallyActivity */
    public void play_manually(View view) {
        final Spinner driver = findViewById(R.id.spinner);
        final Spinner robot = findViewById(R.id.spinner3);
        Intent intent_send = new Intent(this, PlayAnimationActivity.class);
        Bundle extras_send = new Bundle();
        extras_send.putString(DRIVER, driver.getSelectedItem().toString());
        extras_send.putString(ROBOT, robot.getSelectedItem().toString());
        intent_send.putExtras(extras_send);
        startActivity(intent_send);
    }

    /** Called when the user taps the "Play Animation" button
     * Goes to PlayAnimationActivity */
    public void play_animation(View view) {
        final Spinner driver = findViewById(R.id.spinner);
        final Spinner robot = findViewById(R.id.spinner3);
        Intent intent_send = new Intent(this, PlayAnimationActivity.class);
        Bundle extras_send = new Bundle();
        extras_send.putString(DRIVER, driver.getSelectedItem().toString());
        extras_send.putString(ROBOT, robot.getSelectedItem().toString());
        intent_send.putExtras(extras_send);
        startActivity(intent_send);
    }

    public void updateProgressBar() {
        new Thread(new Runnable() {
            public void run() {
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
        MazeHolder.setMaze(mazeConfig);
        // switch to play activity
        final Spinner driver = findViewById(R.id.spinner);
        final Spinner robot = findViewById(R.id.spinner3);
        Intent intent_send;

        // goes to manual activity if "You" is selected, otherwise goes to Animation Activity
        if (driver.getSelectedItem().toString().equals("You"))
            intent_send = new Intent(this, PlayManuallyActivity.class);
        else
            intent_send = new Intent(this, PlayAnimationActivity.class);

        // sends driver and robot info
        Bundle extras_send = new Bundle();
        extras_send.putString(DRIVER, driver.getSelectedItem().toString());
        extras_send.putString(ROBOT, robot.getSelectedItem().toString());
        intent_send.putExtras(extras_send);
        startActivity(intent_send);
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
