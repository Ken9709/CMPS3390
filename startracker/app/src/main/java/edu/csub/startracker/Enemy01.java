package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Enemy 1 class
 */
public class Enemy01 implements GameObject{
    private final float dpi;
    private float x,y,ySpeed;
    private final float width, height;
    private float health = 100f;
    private final Bitmap enemy,enemy_left, enemy_right;
    private Bitmap curImage;
    private int screenWidth, screenHeight;
    private Paint paint = new Paint();


    /**
     * Constructor for an enemy 1
     * @param res resources
     * @param x x position
     * @param y y position
     */
    public Enemy01(Resources res, float x, float y){
        this.x = x;
        this.y = y;
        enemy = BitmapFactory.decodeResource(res, R.mipmap.enemy01);
        enemy_left = BitmapFactory.decodeResource(res, R.mipmap.enemy01_left);
        enemy_right = BitmapFactory.decodeResource(res, R.mipmap.enemy01_right);
        curImage = enemy;
        width = curImage.getWidth();
        height = curImage.getWidth();

        dpi = res.getDisplayMetrics().densityDpi;
        screenWidth = res.getDisplayMetrics().widthPixels;
        screenHeight = res.getDisplayMetrics().heightPixels;

        ySpeed = 0.02f * dpi;
    }

    /**
     * Overriden update method to update when an enemy 1
     * is created
     */
    @Override
    public void update() {
        float x0ff = (float) (0.02f * screenWidth * Math.sin(y / (0.04f * screenHeight)));
        x += x0ff;
        curImage = x0ff > 0 ? enemy_left: enemy_right;
        if(Math.abs(x0ff) <2) curImage = enemy;

        y += ySpeed;

    }

    /**
     * overriden draw method to draw an enemy 1
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(curImage, x, y, paint);
    }

    /**
     * overriden getX position method
     * @return
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * overriden get y position method
     * @return
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
        return 0;
    }
}
