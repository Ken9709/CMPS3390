package edu.csub.snakefinal;



import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;


/**
 *  android Activity for the game
 */
public class GameActivity extends Activity {
     SnakeGame snakeGame;

    /**
     * Oncreate for the game
     * @param savedInstanceState instance state for android save
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //getting the size of the screen to be used for the game display
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        //creating an instance of the game and passing the context and size point to it
        
        snakeGame = new SnakeGame(this, point);
        
        // setting the current view to the active game
        setContentView(snakeGame);
    }

    /**
     * Overriden on resume method to start the game when it is active
     */
    @Override
    protected void onResume(){
        super.onResume();
        snakeGame.resume();
    }

    /**
     * method to end the game when you die and go back to the start screen
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
}

