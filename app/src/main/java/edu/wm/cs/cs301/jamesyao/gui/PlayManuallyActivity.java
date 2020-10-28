package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.wm.cs.cs301.jamesyao.R;

public class PlayManuallyActivity extends AppCompatActivity {

    public static final int TEMP_PATH_LENGTH = 2;
    public static final int TEMP_SHORTEST_PATH_LENGTH = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_playing);
    }

    /** Called when the user taps the "Winning" button */
    public void winning(View view) {
        // send path and energy info
        // store info in bundle, then send with intent
        Intent intent = new Intent(this, WinningActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("Length of Path Taken", TEMP_PATH_LENGTH);
        extras.putInt("Length of Shortest Path", TEMP_SHORTEST_PATH_LENGTH);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /** Called when the user taps the "Losing" button */
    public void losing(View view) {
        // send path and energy info
        // store info in bundle, then send with intent
        Intent intent = new Intent(this, LosingActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("Length of Path Taken", TEMP_PATH_LENGTH);
        extras.putInt("Length of Shortest Path", TEMP_SHORTEST_PATH_LENGTH);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /** Override back button to go to title activity */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AMazeActivity.class));
        finish();
    }
}
