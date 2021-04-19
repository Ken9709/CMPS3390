package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Enemy spawner object that
 * randomly creates enemies on the screen and
 * sends them at the player
 */
public class EnemySpawner {
    private ArrayList<GameObject> enemies;
    float x, y = 0;
    int screenWidth;
    int wave =1, enemy01Spawned = 0, enemy02Spawned = 0;
    int frameTick = 0, spawnTick, waveTick = 0;
    Resources res;
    private Paint paint = new Paint();


    /**
     * Enemy spawner constructor
     * @param res resources from game files
     */
    public EnemySpawner(Resources res){
        enemies = new ArrayList<>();
        screenWidth = res.getDisplayMetrics().widthPixels;
        this.res = res;
        spawnTick = new Random().nextInt(120-60)+60;
        paint.setColor(Color.WHITE);
        paint.setTextSize(screenWidth * 0.05f);
    }

    /**
     * update method to update the game
     * as new enemies are spawned in
     */
    public void update() {
        frameTick++;
        // spawning logic
        //wait a given amount of frames
        if (frameTick >= spawnTick) {
            frameTick = 0;
            spawnTick = new Random().nextInt(120-60)+60;

            //move x to new position
            x = new Random().nextInt((int) (screenWidth * 0.75f - screenWidth * 0.05f)) + screenWidth * 0.05f;

            // choose enemy to spawn
            int tmp = (int) Math.round(Math.random());

            if (tmp == 0 && enemy01Spawned < wave) { //Spawn enemy01
                enemies.add(new Enemy01(res, x, y));
                enemy01Spawned++;
            } else {
                tmp = 1;

            }
            if(tmp == 1 && enemy02Spawned <wave){
                enemies.add(new Enemy02(res, x,y));
                enemy02Spawned++;
            }

        }
        if(enemy01Spawned >= wave && enemy02Spawned >= wave){
            waveTick++;
        }
        if(waveTick >= 240){
            wave++;
            waveTick = 0;
            enemy01Spawned =0;
            enemy02Spawned =0;
        }
        //spawn that enemy and count it

        //if all enemies pre wave have spawned

        //update all enemies
        for(Iterator<GameObject> iterator = enemies.iterator(); iterator.hasNext();){
            GameObject go = iterator.next();
            go.update();
            if(!go.isAlive()){
                iterator.remove();
            }
        }
    }

    /**
     * draw method to draw the enemies onto the
     * screen as they are spawned
     * @param canvas android canvas to be drawn to
     */
    public void draw(Canvas canvas){

        canvas.drawText("wave " + wave, screenWidth* 0.05f, screenWidth *0.05f, paint);
        for(GameObject go : enemies){
            go.draw(canvas);
        }
    }

    /**
     * getter method for the arraylist of
     * enemies to be used for collision detection
     * @return list of enemies
     */
    public ArrayList<GameObject> getEnemies(){
        return enemies;
    }

}
