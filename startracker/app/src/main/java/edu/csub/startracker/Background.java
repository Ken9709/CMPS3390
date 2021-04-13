package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Background object
 */
public class Background {


        private Bitmap background;
        private int screenX, screenY;
        private Paint paint = new Paint();
        private float dpi;

        private float x = 0f;
        private float y = 0f;

        public Background(int screenX, int screenY, Resources res){
            this.screenX = screenX;
            this.screenY = screenY;
            this.background = BitmapFactory.decodeResource(res, R.mipmap.background);
            this.background = Bitmap.createScaledBitmap(this.background, screenX, screenY, false);
            this.dpi = res.getDisplayMetrics().densityDpi;
    }

    /**
     * getter for the x distance
     * @return x distance
     */
    public float getX() {
        return x;
    }

    /**
     * setter method for the x distance
     * @param x horizontal distance
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * getter method for the y distance
     * @return y distance
     */
    public float getY() {
        return y;
    }

    /**
     * setter method for the y distance
     * @param y distance
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * update method to move the background screen
     */
    public void update() {
            this.y += 0.006f * dpi;

            if(this.y >screenY){
                this.y =-screenY;
            }
    }

    /**
     * draw method to draw the background objects
     * @param canvas canvas to draw on
     */
    public void draw(Canvas canvas) {
         canvas.drawBitmap(this.background, this.x, this.y, paint);
    }
}
