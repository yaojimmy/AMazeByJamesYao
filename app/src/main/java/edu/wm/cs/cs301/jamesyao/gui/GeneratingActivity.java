package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.wm.cs.cs301.jamesyao.R;

public class GeneratingActivity extends AppCompatActivity {

    public static final String MANUAL_MESSAGE = "edu.wm.cs.cs301.jamesyao.MANUAL";
    public static final String ANIMATION_MESSAGE = "edu.wm.cs.cs301.jamesyao.ANIMATION";
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

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView11);
        String message = "Difficulty: " + difficulty + " Maze Type: " + maze_type + " Rooms: " + rooms;
        textView.setText(message);
    }

    /** Called when the user taps the "Play Manually" button */
    public void play_manually(View view) {
        // do something in response to the button
        Intent intent = new Intent(this, PlayManuallyActivity.class);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        intent.putExtra(MANUAL_MESSAGE, progressBar.getProgress());
        startActivity(intent);
    }

    /** Called when the user taps the "Play Animation" button */
    public void play_animation(View view) {
        // do something in response to the button
        Intent intent = new Intent(this, PlayAnimationActivity.class);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        intent.putExtra(ANIMATION_MESSAGE, progressBar.getProgress());
        startActivity(intent);
    }
}
