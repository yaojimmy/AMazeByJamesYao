package edu.wm.cs.cs301.jamesyao.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ToggleButton;

import edu.wm.cs.cs301.jamesyao.R;

public class AMazeActivity extends AppCompatActivity {

    public static final String DIFFICULTY = "edu.wm.cs.cs301.jamesyao.DIFFICULTY";
    public static final String MAZE_TYPE = "edu.wm.cs.cs301.jamesyao.MAZE_TYPE";
    public static final String ROOMS = "edu.wm.cs.cs301.jamesyao.ROOMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mazeactivity_main);

    }

    /** Called when the user taps the "Explore" (or "Revisit") button
     * Sends the difficulty level, maze type, and presence of rooms information to GeneratingActivity.java
     */
    public void explore(View view) {
        // send information to GeneratingActivity
        Intent intent = new Intent(this, GeneratingActivity.class);

        // hold information being sent with intent
        Bundle extras = new Bundle();

        // putting information in the "extras" bundle
        SeekBar seekBar = findViewById(R.id.seekBar);
        extras.putInt(DIFFICULTY, seekBar.getProgress());
        Spinner spinner = findViewById(R.id.spinner2);
        extras.putString(MAZE_TYPE, spinner.getSelectedItem().toString());
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        extras.putBoolean(ROOMS, toggleButton.isChecked());

        intent.putExtras(extras);
        startActivity(intent);
    }
}
