package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.wm.cs.cs301.jamesyao.R;

public class PlayAnimationActivity extends AppCompatActivity {

    public static final String WINNING_MESSAGE = "edu.wm.cs.cs301.jamesyao.WINNING";
    public static final String LOSING_MESSAGE = "edu.wm.cs.cs301.jamesyao.LOSING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_playing);
    }

    /** Called when the user taps the "Winning" button */
    public void winning(View view) {
        // go to winning screen activity
        Intent intent = new Intent(this, WinningActivity.class);
        TextView textView = findViewById(R.id.textView2);
        intent.putExtra(WINNING_MESSAGE, textView.getText());
        startActivity(intent);
    }

    /** Called when the user taps the "Losing" button */
    public void losing(View view) {
        // go to losing screen activity
        Intent intent = new Intent(this, LosingActivity.class);
        TextView textView = findViewById(R.id.textView2);
        intent.putExtra(LOSING_MESSAGE, textView.getText());
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
