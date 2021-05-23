package edu.csub.snakefinal;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Game view and gameplay loop
 */
class SnakeGame extends SurfaceView implements Runnable {


    private Thread thread = null;
    private Context context;
    public enum Direction {UP, RIGHT, DOWN, LEFT}
    // starting direction
    private Direction direction = Direction.UP;
    private int screenX;
    private int screenY;
    private int blockSize;
    private final int BLOCKS_WIDE = 40;
    private int numBlocksHigh;
    private int[] snakeXs;
    private int[] snakeYs;
    private boolean isPlaying;
    private long nextFrameTime;
    private int snakeLength;
    private int appleX;
    private int appleY;
    private final long FPS = 10;
    private int score;
    private GameActivity gameActivity;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;


    /**
     * Game constructor
     * @param context context of the game activity
     * @param size size of the screen
     */
    public SnakeGame(GameActivity context, Point size) {
        super(context);

        screenX = size.x;
        screenY = size.y;

        // find the block size
        blockSize = screenX / BLOCKS_WIDE;
        numBlocksHigh = screenY / blockSize;

        // Initialize the objects to draw
        surfaceHolder = getHolder();
        paint = new Paint();

        gameActivity =context;
        // Setup the array of the snake, if you're a snake pro
        //the game is gonna crash
        snakeXs = new int [300];
        snakeYs = new int [300];
        // Start the game
        newGame();
    }

    /**
     * main game loop for the running thread
     */
    @Override
    public void run() {
        // while the game is running
        while (isPlaying) {
            // if we are on an update frame
            // Update
           if(updateRequired()) {
                update();
                draw();
            }

        }
   }

    /**
     * method to resume the game
     */

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }


    /**
     * method to start the game
     */
    public void newGame() {
        // Start with a single snake piece
        snakeLength = 1;
        snakeXs[0] = BLOCKS_WIDE / 2;
        snakeYs[0] = numBlocksHigh / 2;

        // Create an apple
        spawnApple();

        // Reset the score
        score = 0;

        // Setup nextFrameTime so an update is triggered
        nextFrameTime = System.currentTimeMillis();
    }


    /**
     * Method to check timings so that the game runs slowly
     * and steadily
     * @return a true or false value of if the game is to be updated or not
     */

    public boolean updateRequired() {


        if(nextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            nextFrameTime =System.currentTimeMillis() + 1000 / FPS;

            // Return true so that the update and draw
            // functions are executed
            return true;
        }

        return false;
    }

    /**
     * Method to spawn an apple in a random spot
     */
    public void spawnApple() {
        //random number generator
        Random random = new Random();
        // set a random x coordinate within the bounds
        appleX = random.nextInt(BLOCKS_WIDE - 1) + 1;
        //set a random y coordinate within the bounds
        appleY = random.nextInt(numBlocksHigh - 1) + 1;
    }

    /**
     * Method to eat an apple, spawning a new one and incrementing the score
     */
    private void eatApple(){
        //spawn a new apple
        spawnApple();
        //increment score
        score++;
        // increment the snake length
        snakeLength++;
    }


    /**
     * Method to move the snake
     */
    private void moveSnake(){
        for (int i = snakeLength; i > 0; i--) {
            // Each piece of the snake moves into the position of the piece
            // in front of it, the head does not move here.
            snakeXs[i] = snakeXs[i - 1];
            snakeYs[i] = snakeYs[i - 1];
        }

        // Now move the snake head in the appropriate direction
        switch (direction) {
            case UP:
                snakeYs[0]--;
                break;

            case RIGHT:
                snakeXs[0]++;
                break;

            case DOWN:
                snakeYs[0]++;
                break;

            case LEFT:
                snakeXs[0]--;
                break;
        }
    }

    /**
     * Method to figure out when the snake is dead
     * @return boolean value, whether snake is alive or not
     */
    private boolean detectDeath(){
        // know if the game is lost or not
        boolean dead = false;

        // Die when the snake touches the edge of the screen
        if (snakeXs[0] == -1) dead = true;
        if (snakeXs[0] >= BLOCKS_WIDE) dead = true;
        if (snakeYs[0] == -1) dead = true;
        if (snakeYs[0] == numBlocksHigh) dead = true;

        // Die when the snake touches its tail
        for (int i = snakeLength - 1; i > 0; i--) {
            if ((i > 4) && (snakeXs[0] == snakeXs[i]) && (snakeYs[0] == snakeYs[i])) {
                dead = true;
            }
        }

        return dead;
    }

    /**
     * Update function to check when an apple is eaten or if the snake is dead
     */
    public void update() {
        // check if the snake ate an apple
        if (snakeXs[0] == appleX && snakeYs[0] == appleY) {
            eatApple();
        }

        moveSnake();

        if (detectDeath()) {
            //game restarts
            gameActivity.gameOver();
        }
    }

    /**
     * Draw method to color the screen and create
     * the visible game pieces
     */
    public void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();

            // color the game screen
            canvas.drawColor(Color.argb(255, 120, 100, 90));

            // Set the color of the paint to draw the snake black
            paint.setColor(Color.argb(255, 0, 0, 0));

            // set the score text
            paint.setTextSize(20);
            canvas.drawText("Score:" + score, 10, 70, paint);

            // Make the snake
            for (int i = 0; i < snakeLength; i++) {
                //drawing rectangles in an array
                canvas.drawRect(snakeXs[i] * blockSize,
                        (snakeYs[i] * blockSize),
                        (snakeXs[i] * blockSize) + blockSize,
                        (snakeYs[i] * blockSize) + blockSize,
                        paint);
            }

            // Set the color of the paint to draw Apple red
            paint.setColor(Color.argb(255, 255, 0, 0));

            // Draw Apple
            canvas.drawRect(appleX * blockSize,
                    (appleY * blockSize),
                    (appleX * blockSize) + blockSize,
                    (appleY * blockSize) + blockSize,
                    paint);

            // Unlock the canvas and reveal the graphics for this frame
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }




    /**
     * Method to detect touch and move the snake
     * @param motionEvent touch
     * @return a new direction of the snake
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

       // determine if clicked on the right or left of screen,
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                // if on the right side, go clockwise
                if (motionEvent.getX() >= screenX / 2) {
                    switch(direction){
                        case UP:
                            direction = Direction.RIGHT;
                            break;
                        case RIGHT:
                            direction = Direction.DOWN;
                            break;
                        case DOWN:
                            direction = Direction.LEFT;
                            break;
                        case LEFT:
                            direction = Direction.UP;
                            break;
                    }

                } else {
                    //if on the left side, go counter clockwise
                    switch(direction){
                        case UP:
                            direction = Direction.LEFT;
                            break;
                        case LEFT:
                            direction = Direction.DOWN;
                            break;
                        case DOWN:
                            direction = Direction.RIGHT;
                            break;
                        case RIGHT:
                            direction = Direction.UP;
                            break;
                    }
                }
        }
        return true;
    }
}
