package edu.csub.startracker;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Singleton class to control the high score for a given player
 * and its list
 */
public final class HighScore {
    private static final HighScore INSTANCE = new HighScore();
    private int curScore = 0;
    private int highScore =0;
    private String name = "Player1";
    private FirebaseFirestore db;

    /**
     * Constructor for the highscore and its reveference to the database for
     * the game
     */
    private HighScore(){
        db = FirebaseFirestore.getInstance();

    }

    /**
     * getter for the instance of the high score
     * @return instance
     */
    public static HighScore getInstance(){
        return INSTANCE;
    }

    /**
     * method to add score when a bad guy is shot
     * @param score score of the player in the game
     */
    public void addScore(int score){
        curScore += score;
        if(curScore > highScore){
            highScore = curScore;
        }
    }

    /**
     * Getter for the current score of a game
     * @return current score
     */
    public int getCurScore() {
        return curScore;
    }

    /**
     * getter for the high score of the player
     * @return high score
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * method to reset the current score after a game ends
     */
    public void resetCurScore() {
        curScore = 0;
    }

    /**
     * setter for the player's name
     * @param playerName player's name
     */
    public void setPlayerName(String playerName) {
    name = playerName;
    }

    /**
     * getter for the Players name
     * @return player name
     */
    public String getPlayerName() {
        return name;
    }


    /**
     * Database call method for the high scores list
     * @param howMany How many people you want on the leaderboard
     * @param highScores high scores list
     * @param context game context (main activity)
     */
    public void getHighScores(int howMany, ListView highScores, Context context){
        ArrayList<String> topScores = new ArrayList<>();
        db.collection("HighScore")
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(howMany)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot doc : task.getResult()){
                                String tmpString = String.format("%s: %s", doc.getId(),
                                doc.get("score"));
                                topScores.add(tmpString);
                                Log.d("SCORE", tmpString);
                            }
                            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(context,
                                    android.R.layout.simple_list_item_1, topScores);
                            highScores.setAdapter(itemsAdapter);
                        }
                    }
                });

    }

    /**
     * Method to post the high scores to the database after one is achieved
     */
    public void postHighScore(){
        Map<String, Integer> hs = new HashMap<>();
        hs.put("score", highScore);

        db.collection("HighScore").document(name)
                .set(hs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    Log.d("data", name+ "'s score was set");
                    }
                });


    }
}
