package edu.csub.snakefinal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main starting activity for the game
 * @author Kenneth Wood
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {


    /**
     * Overriden oncreate method for when the game is launched
     * @param savedInstanceState a saved state of the game
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * method to move to the game activity when the play button is clicked
     */

    public void onPlayButtonClicked(View view) {
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }

    


}
