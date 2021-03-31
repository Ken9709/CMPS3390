package kwood11.a10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Main Activity/front page of our todolist application
 * @author Kenny Wood
 * @version 1.0
 */


public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private ListView listView;
    private EditText input;
    private TabLayout tabs;
    private ArrayList<ListItem> items;
    private ArrayAdapter<ListItem> itemsAdapter;
    private String selectedCollection ="Todo";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    /**
     * Overriden Creation function for when the app starts.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        setupLongClickHandler();


        input = findViewById(R.id.etInput);
        input.setOnEditorActionListener(this);
        tabs = findViewById(R.id.tabLayout);
        
        setupTabClickListener();

        updateList();
    }

    private void updateList() {
        showToast("Getting List", Toast.LENGTH_SHORT);
        Database.getList(db, selectedCollection, items, itemsAdapter);
    }


    /**
     * Listener function for when one of the tabs is clicked
     */
    private void setupTabClickListener() {
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Clear Items
                items.clear();

                //Set the selectecd collection
                selectedCollection = tab.getText().toString();
                // Load Items From Table
                updateList();
                //Notify dataset changed
                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * Handler function for when an item is clicked
     * and held, deleting the item
     */
    private void setupLongClickHandler() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem tmpItem = items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                Database.removeItem(db,selectedCollection, items, itemsAdapter, tmpItem);
                showToast("Deleted Item", Toast.LENGTH_SHORT);
                return false;
            }
        });
    }

    /**
     * Function to show a message when things are added or removed
     * @param message The message to show
     * @param length The lenght of the message.
     */
    private void showToast(String message, int length) {
        Toast toast = Toast.makeText(this, message, length);
        toast.setGravity(Gravity.CENTER, 0, -30);
        toast.show();
        
    }

    /**
     * Function to handle when an item is inputted into the list by the user
     * @param v Textview
     * @param actionId The action occuring
     * @param event event
     * @return
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (event == null || event.getAction() == KeyEvent.ACTION_UP) {
            ListItem tmpItem = new ListItem(input.getText().toString());
            items.add(tmpItem);
            input.setText("");
            itemsAdapter.notifyDataSetChanged();
            Database.add(db, selectedCollection, tmpItem);
            showToast("Item Added", Toast.LENGTH_SHORT);
        }
            return true;

    }
}