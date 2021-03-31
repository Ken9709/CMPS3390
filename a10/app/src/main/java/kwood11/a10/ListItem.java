package kwood11.a10;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * ListItems class the contain the items in our lists
 * and display them
 */
public class ListItem implements Serializable {
    private long dttm;

    public String getItem() {
        return item;
    }

    private String item;

    /**
     * Overriden constructor item for a list with just an item,
     * no time given
     * @param item the item added
     */
    public ListItem(String item) {
        this.item = item;
        dttm = System.nanoTime();
    }

    /**
     * Overriden constructor given a date time and the item
     * @param dttm given date time
     * @param item the item added
     */
    public ListItem(long dttm, String item) {
        this.item = item;
        this.dttm = dttm;
    }


    /**
     * Overriden tostring method for our list
     * @return an item to the list
     */
    @NonNull
    @Override
    public String toString(){
        return item;
    }

    /**
     * Getter method for the date time
     * @return date time
     */
    public long getDttm() {
        return dttm;
    }
}
