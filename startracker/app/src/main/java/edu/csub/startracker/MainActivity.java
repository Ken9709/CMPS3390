package edu.csub.startracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Main Activity page for the game
 * @author Kenneth Wood
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Oncreate method for when the app is opened
     * @param savedInstanceState the gamestate saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * Event to open the game view when the start button is clicked
     * @param view the game view
     */
    public void onPlayButtonClicked(View view) {
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }
}