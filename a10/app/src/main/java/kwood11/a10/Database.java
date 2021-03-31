package kwood11.a10;

import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Database class, to contain methods to update and remove things from the database
 * as well as pull data from it.
 */
public class Database {
    public static void add(FirebaseFirestore db, String selectedCollection, ListItem item) {
        Map<String, Object> listItem = new HashMap<>();
        listItem.put("item", item);

        db.collection(selectedCollection)
                .add(listItem)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("DATABASE", "Item added: " + documentReference);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("DATABASE", "Failed to add item: " + Arrays.toString(e.getStackTrace()));
                    }
                });
    }

    /**
     * Method to get one of the lists of items
     * @param db the database itself
     * @param selectedCollection the collection we are on
     * @param items items of the list
     * @param itemsAdapter the items adapter to change the order
     */
        public static void getList(FirebaseFirestore db, String selectedCollection,
                                   ArrayList<ListItem> items, ArrayAdapter<ListItem> itemsAdapter) {

            db.collection(selectedCollection)
                    .orderBy("item.dttm")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                long dttm = doc.getLong("item.dttm");
                                String item = doc.getString("item.item");
                                items.add(new ListItem(dttm, item));
                            }
                            itemsAdapter.notifyDataSetChanged();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("DATABASE", "Failed to get list: " +  Arrays.toString(e.getStackTrace()));
                        }
                    });
        }

    /**
     * Method to remove an item from the database when it is long clicked
     * @param db the database itself
     * @param selectedCollecction the collection we are on
     * @param items items of the list
     * @param itemsAdapter adapter to order the items
     * @param removeditem the item long clicked to be removed
     */
        public static void removeItem(FirebaseFirestore db, String selectedCollecction, ArrayList<ListItem>
                                      items, ArrayAdapter<ListItem> itemsAdapter, ListItem removeditem){
            db.collection(selectedCollecction).whereEqualTo("item.dttm", removeditem.getDttm())
                    .whereEqualTo("item.item", removeditem.getItem())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(DocumentSnapshot doc: queryDocumentSnapshots) {
                                db.collection(selectedCollecction).document(doc.getId()).delete();
                            }
                        }
                    });
        }
}
