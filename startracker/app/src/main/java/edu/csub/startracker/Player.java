package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Player class, contains the player owning the game
 * that starts and all attributes that need to be tracked for
 * the gamestate.
 */
public class Player implements GameObject{

    private float x,y, prevX, prevY;
    private final Bitmap playerImg;
    private final Bitmap playerLeft;
    private final Bitmap playerRight;
    private Bitmap curImage;
    private Paint paint = new Paint();
    private final float dpi;
    private int frameTicks = 0, shotTicks =0;
    private final Resources res;
    private final int width, height;


    ArrayList<Laser> lasers = new ArrayList<>();
    private float health = 100f;

    /**
     * Player constructor
     * @param res resources
     */
    public Player(Resources res){
        this.res = res;
        playerImg = BitmapFactory.decodeResource(res, R.mipmap.player);
        playerLeft = BitmapFactory.decodeResource(res, R.mipmap.player_left);
        playerRight = BitmapFactory.decodeResource(res, R.mipmap.player_right);

        curImage = playerImg;
        width = curImage.getWidth();
        height = curImage.getHeight();

        DisplayMetrics dm = res.getDisplayMetrics();
        dpi = dm.densityDpi;

        x = (dm.widthPixels /2f) - (playerImg.getWidth() /2f);
        y = (dm.heightPixels *.75f);



    }


    public void updateTouch(int touchX, int touchY) {
        if (touchX > 0 && touchY > 0) {
            this.x = touchX - (playerImg.getWidth() / 2f);
            this.y = touchY - (playerImg.getHeight() / 2f);
        }
    }
    /**
     * Update function for the game that causes the gamestate
     * to update
     */

    @Override
    public void update(){


        if(health <=0) return;
        if(Math.abs(x - prevX) < 0.04 * dpi) {
            frameTicks++;
        } else { frameTicks =0;
        }
            if(this.x < prevX -0.04 * dpi){
                curImage = playerLeft;
            } else if(this.x >prevX + 0.04 *dpi){
                curImage =playerRight;
            } else if (frameTicks> 5) {
                curImage = playerImg;
            }

        prevX = x;
        prevY = y;

        //Increases shotTicks
        shotTicks++;

        // see if we need to shoot
        if(shotTicks >= 30){
            //shoot
            Laser tmp = new Laser(this.res);
            tmp.setX(x + (playerImg.getWidth() / 2f) - tmp.getMidX());
            tmp.setY(y - tmp.getHeight() /2f);

            lasers.add(tmp);
            //resets
            shotTicks =0;
        }
        // remove lasers that are off screen
        for(Iterator<Laser> iterator = lasers. iterator(); iterator.hasNext();){
            Laser laser =iterator.next();
            if(!laser.isOnScreen())
                iterator.remove();

                }


        // update all lasers
        for(Laser laser : lasers){
            laser.update();
            }
    }



    /**
     * Draw function to draw the icons and background and lasers
     * @param canvas the canvas to be drawn on
     */
    public void draw(Canvas canvas){
        if(health <= 0) return;
        canvas.drawBitmap(curImage, this.x, this.y,this.paint);


       for(Laser laser : lasers){
           laser.draw(canvas);
       }
    }

    /**
     *  getter method for x position of player
     * @return y
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * getter method for y position of the player
     * @return y
     */
    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public boolean isAlive() {
        return health >0f;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public float takeDamage(float damage) {
        return health -= damage;
    }

    @Override
    public float addHealth(float repairAmount) {
        return health += repairAmount;
    }

    public ArrayList<Laser> getLasers(){
        return lasers;
    }
}
