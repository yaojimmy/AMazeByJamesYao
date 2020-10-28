package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.wm.cs.cs301.jamesyao.R;

public class LosingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losing);
    }

    /** Override back button to go to title activity */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AMazeActivity.class));
        finish();
    }
}
