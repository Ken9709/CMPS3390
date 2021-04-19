package edu.csub.startracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;

/**
 * Activity for the game
 */
public class GameActivity extends AppCompatActivity {
    private GameView gameView;

    /**
     * Oncreate for the game
     * @param savedInstanceState instance state for android save
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);

        setContentView(gameView);


    }

    /**
     * Method to be used when the player dies, resulting
     * in a game over.
     */

    public void gameOver(){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                finish();
            }
        }, 6000);
    }

    /**
     *
     *  Override method for the when onPause when the game is paused
     *   by exiting the app
     *
     */
    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    /** Override method for onResume when the app is resumed
     *
     */
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}