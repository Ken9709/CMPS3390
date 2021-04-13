package edu.csub.startracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;


/**
 * View of the game itself
 */
public class GameView extends SurfaceView implements Runnable{

    private final Background background1;
    private final Background background2;
    private boolean isPlaying = true;
    private Thread thread;
    private int touchX,touchY;

    private final Player player;

    /**
     * The game view
     * @param context the context of the game
     * @param screenX x position on the screen
     *  @param screenY y position on the screen
     */
    public GameView(Context context, int screenX, int screenY) {
        super(context);

        Resources res = getResources();

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.setY(screenY);

        player = new Player(res);
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
        player.update(touchX, touchY);
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
            player.draw(canvas);
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
