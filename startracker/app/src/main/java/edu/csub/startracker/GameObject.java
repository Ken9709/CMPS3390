package edu.csub.startracker;

import android.graphics.Canvas;


/**
 * Interace setup and methods for an object
 * to allow health information and damage
 * to be shared between the objects.
 */
public interface GameObject {
    void update();
    void draw(Canvas canvas);

    float getX();
    float getY();
    float getWidth();
    float getHeight();

    boolean isAlive();
    float getHealth();
    float takeDamage(float damage);
    float addHealth(float repairAmount);
}
