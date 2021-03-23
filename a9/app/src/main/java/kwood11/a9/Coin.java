package kwood11.a9;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * Coin object class to contain the names and values of the coins we will use
 */
public class Coin extends BaseObservable {
    private String name;
    private double curValue;

    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * coin constructor
     * @param name name of the coin
     */
    public Coin(String name) {
        this.name = name;
    }

    /**
     * getter for the name
     * @return coins name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the name
     * @param name coins name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the current value, bound
     * @return  coins current value
     * */
    @Bindable
    public String getCurValue() {
        return numberFormat.format(curValue);
    }

    /**
     * setter for the current value
     * @param curValue current value of the coin
     */
    public void setCurValue(double curValue) {
        this.curValue = curValue;
        notifyPropertyChanged(BR.curValue);
    }

}
