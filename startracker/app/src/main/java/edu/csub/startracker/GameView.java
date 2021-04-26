package edu.csub.startracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;


/**
 * View of the game itself
 */
public class GameView extends SurfaceView implements Runnable{

    private final Background background1;
    private final Background background2;
    private boolean isPlaying = true;
    private Thread thread;
    private int touchX,touchY;
    private ArrayList<Laser> lasers;
    private ArrayList<GameObject> enemies;
    private GameActivity gameActivity;

    private final Player player;

    private EnemySpawner spawner;
    private final float screenWidth, screenHeight;
    private Paint textPaint = new Paint();
    private Paint highScorePaint = new Paint();
    private HighScore highScore = HighScore.getInstance();

    /**
     * The game view
     * @param context the context of the game
     * @param screenX x position on the screen
     *  @param screenY y position on the screen
     */
    public GameView(GameActivity context, int screenX, int screenY) {
        super(context);


        Resources res = getResources();
        screenWidth = res.getDisplayMetrics().widthPixels;
        screenHeight = res.getDisplayMetrics().heightPixels;
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(screenWidth * 0.1f);
        highScorePaint.setColor(Color.WHITE);
        highScorePaint.setTextSize(screenWidth *0.06f);

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.setY(screenY);

        player = new Player(res);
        spawner = new EnemySpawner(res);

        lasers = player.getLasers();
        enemies = spawner.getEnemies();

        gameActivity = context;


    }

    /**
     * Override method for when the screen is touched
     * @param event event for movement of the touch screen
     * @return boolean true
     */
    @Override
    public boolean onTouchEvent(MotionEvent event){
        touchX = (int)event.getX();
        touchY = (int)event.getY();
        return true;
    }

    /**
     * Override method for the gameplay loop
     */
    @Override
    public void run() {
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    /**
     * update method the moves the background and player
     * in real time
     */
    private void update() {
        background1.update();
        background2.update();
        player.updateTouch(touchX, touchY);
        player.update();
        spawner.update();
        checkAllCollisions();
        checkEnemiesOffScreen();
    }

    /**
     * Method to check if an enemy has passed the screen
     * which kills the player
     */
    private void checkEnemiesOffScreen() {
        for(GameObject go: enemies){
            if(go.getY() > screenHeight){
                player.takeDamage(100);
                go.takeDamage(100);
                gameActivity.gameOver();
            }
        }
    }

    /**
     * Collision detection method for lasers
     */
    private void checkAllCollisions(){
        for(Laser laser :lasers){
            for(GameObject go: enemies){
                if(checkCollision(laser,go)){
                    laser.takeDamage(100);
                    go.takeDamage(25);
                    highScore.addScore(25);
                }
            }
        }

        for(GameObject go : enemies){
            if (checkCollision(player, go)){
                player.takeDamage(100);
                go.takeDamage(100);
                gameActivity.gameOver();
            }
        }


    }

    /**
     * Collision checking algorithm
     * @param g1 object 1
     * @param g2 object 2
     * @return true or false based on collision
     */
    private boolean checkCollision(GameObject g1, GameObject g2){
        return g1.getX() < g2.getX() + g2.getWidth() &&
                g1.getX() + g1.getWidth() > g2.getX() &&
                g1.getY() < g2.getY() + g2.getHeight() &&
                g1.getY() + g1.getHeight() >g2.getY();

    }

    /**
     * draw method that draws the objects on the screen as they are
     * moved
     */
    private void draw(){
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            background1.draw(canvas);
            background2.draw(canvas);

            if(!player.isAlive()){
                canvas.drawText("GAME OVER", screenWidth/ 4f, screenHeight /2f,textPaint);
            }

            canvas.drawText(String.format("Score: %s", highScore.getCurScore()), screenWidth *0.02f,
                    screenHeight * 0.06f, highScorePaint);
            player.draw(canvas);
            spawner.draw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    /**
     * sleep method to have mini pauses for the gamestate to update
     */
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * pause method for the game to stop when paused
     */
    public void pause(){
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * resume method to restart the game when it is unpaused.
     */
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }
}
