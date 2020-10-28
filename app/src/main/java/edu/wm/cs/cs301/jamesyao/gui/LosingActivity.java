package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.wm.cs.cs301.jamesyao.R;

public class LosingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losing);

        // Get the Intent that started this activity and extract the extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // get the path length and energy values
        int length_of_path_taken = extras.getInt("Length of Path Taken");
        int length_of_shortest_path = extras.getInt("Length of Shortest Path");
        int overall_energy_consumption = extras.getInt("Energy Consumed");

        // put info in a message
        String message = "Length of path taken: " + length_of_path_taken;
        message = message + ", Length of shortest path: " + length_of_shortest_path;
        if (overall_energy_consumption != 0) {
            message = message + ", Overall energy consumption: " + overall_energy_consumption;
        }

        // show info with textView
        TextView textView = findViewById(R.id.textView22);
        textView.setText(message);
    }

    /** Override back button to go to title activity */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AMazeActivity.class));
        finish();
    }
}
