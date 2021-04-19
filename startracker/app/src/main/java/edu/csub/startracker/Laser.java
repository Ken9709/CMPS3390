package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * object class for the lasers being shot from our ship
 */
public class Laser implements GameObject{

    float x,y;
    Bitmap laser;
    private float dpi;
    private Paint paint = new Paint();
    private float health = 100f;
    private final int width, height;

    /**
     * Constructor for the laser object
     * @param res resources
     */
    public Laser(Resources res) {
        laser = BitmapFactory.decodeResource(res, R.mipmap.bullet);
        width = laser.getWidth();
        height = laser.getHeight();
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
    @Override
    public float getHeight(){
        return height;
    }

    /**
     * interface method to check if an object is alive
     * @return alive or dead boolean
     */
    @Override
    public boolean isAlive() {
        return health >0f;
    }

    /**
     * interface method to get the health of a laser
     * @return health
     */
    @Override
    public float getHealth() {
        return health;
    }

    /**
     * Interface method to take damage to an object
     * @param damage damage taken
     * @return overall health
     */
    @Override
    public float takeDamage(float damage) {
        return health -= damage;
    }

    /**
     * method to add health after a pickup
     * @param repairAmount amount being healed
     * @return health after heal
     */
    @Override
    public float addHealth(float repairAmount) {
        return health += repairAmount;
    }

    /**
     * getter for x
     * @return x position
     */
    @Override
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
    @Override
    public float getY() {
        return y;
    }

    /**
     * getter for width of laser
     * @return width
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * setter for the y position
     * @param y y position
     */
    public void setY(float y) {
        this.y = y;
    }
}
