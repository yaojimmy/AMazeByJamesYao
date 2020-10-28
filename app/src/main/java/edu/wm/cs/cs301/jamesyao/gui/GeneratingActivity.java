package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.wm.cs.cs301.jamesyao.R;

public class GeneratingActivity extends AppCompatActivity {

    private static final String MANUAL_MESSAGE = "edu.wm.cs.cs301.jamesyao.MANUAL";
    private static final String ANIMATION_MESSAGE = "edu.wm.cs.cs301.jamesyao.ANIMATION";
    private static final String TAG = "CodeRunner";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);

        // Get the Intent that started this activity and extract the extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // get the values selected by users in the title screen
        int difficulty = extras.getInt(AMazeActivity.DIFFICULTY);
        String maze_type = extras.getString(AMazeActivity.MAZE_TYPE);
        boolean rooms = extras.getBoolean(AMazeActivity.ROOMS);

        // Capture the layout's TextView and set the string as its text (for now)
        TextView textView = findViewById(R.id.textView11);
        String message = "Difficulty: " + difficulty + " Maze Type: " + maze_type + " Rooms: " + rooms;
        textView.setText(message);

        /**
         * Robot and driver declaration, which are initialized with their respective spinner values when this activity ends
         * Maze generation goes here
         */
        updateProgressBar();
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
            final ProgressBar progressBar = findViewById(R.id.progressBar);
            public void run() {
                // update progress bar by 1, wait one second, repeat 100x
                Log.i(TAG, "run: starting thread for 100 second");
                for (int i = 0; i < 100; i++) {
                    progressBar.setProgress(progressBar.getProgress() + 1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
