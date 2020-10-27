package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import edu.wm.cs.cs301.jamesyao.R;

public class GeneratingActivity extends AppCompatActivity {

    public static final String MANUAL_MESSAGE = "edu.wm.cs.cs301.jamesyao.MANUAL";
    public static final String ANIMATION_MESSAGE = "edu.wm.cs.cs301.jamesyao.ANIMATION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);

        // Get the Intent that started this activity and extract the string
        //Intent intent = getIntent();
        //String message = intent.getStringExtra(AMazeActivity.EXPLORE_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        //TextView textView = findViewById(R.id.textView);
        //textView.setText(message);
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
        intent.putExtra(MANUAL_MESSAGE, progressBar.getProgress());
        startActivity(intent);
    }
}
