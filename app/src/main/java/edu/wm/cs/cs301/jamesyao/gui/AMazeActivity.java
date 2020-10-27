package edu.wm.cs.cs301.jamesyao.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import edu.wm.cs.cs301.jamesyao.R;

public class AMazeActivity extends AppCompatActivity {

    public static final String EXPLORE_MESSAGE = "edu.wm.cs.cs301.jamesyao.EXPLORE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mazeactivity_main);
    }

    /** Called when the user taps the "Send" button */
    public void explore(View view) {
        // do something in response to the button
        Intent intent = new Intent(this, GeneratingActivity.class);
        SeekBar seekBar = findViewById(R.id.seekBar);
        intent.putExtra(EXPLORE_MESSAGE, seekBar.getProgress());
        startActivity(intent);
    }
}
