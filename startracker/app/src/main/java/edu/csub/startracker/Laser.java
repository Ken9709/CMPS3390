package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * object class for the lasers being shot from our ship
 */
public class Laser {

    float x,y;
    Bitmap laser;
    private float dpi;
    private Paint paint = new Paint();

    /**
     * Constructor for the laser object
     * @param res resources
     */
    public Laser(Resources res) {
        laser = BitmapFactory.decodeResource(res, R.mipmap.bullet);
        dpi = res.getDisplayMetrics().densityDpi;
    }

    /**
     * Check for whether or not a laser is on screen or not
     * @return true or false based on whether the laser is on screen
     */
    public boolean isOnScreen() {
        return !(y < getHeight());
    }

    /**
     * update the position of the laser
     */
    public void update(){
        y -= 0.1f * dpi;
    }

    /**
     * draw the laser
     * @param canvas canvas used to draw laser objects
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(laser, this.x, this.y, this.paint);
    }

    /**
     * getter for the midpoint of the screen
     * @return mid width
     */
    public float getMidX(){
        return laser.getWidth() / 2f;
    }

    /**
     * getter for the height
     * @return height of the laser
     */
    public float getHeight(){
        return laser.getHeight();
    }

    /**
     * getter for x
     * @return x position
     */
    public float getX() {
        return x;
    }

    /**
     * setter for the x position
     * @param x position
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * getter for the y position
     * @return y position
     */
    public float getY() {
        return y;
    }

    /**
     * setter for the y position
     * @param y y position
     */
    public void setY(float y) {
        this.y = y;
    }
}
