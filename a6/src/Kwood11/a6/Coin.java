package Kwood11.a6;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.chart.XYChart;


/**
 * Coin object to hold all of the values,
 * data, information we are retrieving and need to populate the
 * application and its chart
 * @author Kenneth Wood
 * @version 1.0
 */
public class Coin {
    private String name;
    private final DoubleProperty currentPrice;
    XYChart.Series<Number, Number> historicalValues;

    /**
     * Default constructor for the coin object
     * @param name name of the coin
     */
    public Coin(String name) {
        historicalValues = new XYChart.Series<>();
        historicalValues.setName(name);
        currentPrice = new SimpleDoubleProperty();
        this.name =name;
    }

    /**
     * getter method for Historical Values
     * @return historical values of the specified coin
     */
    public XYChart.Series<Number, Number> getHistoricalValues() {
        return historicalValues;
    }

    /**
     * setter method for Historical values
     * @param series a series of historical values recieved from the API
     */
    public void setHistoricalValues(XYChart.Series<Number, Number> series) {
        this.historicalValues = historicalValues;
    }

    /**
     * Method to add values onto an existing coin after it is selected
     * in the dropdown menu
     * @param day amount of days selected
     * @param value value of the coin given
     */
    public void addHistoricalValue(int day, double value){
        historicalValues.getData().add(new XYChart.Data<>(day,value));
    }

    /**
     * setter method for the coins name
     * @param name name of the coin
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method for the coins name
     * @return name of the coin
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for the current price of the given
     * coin
     * @return current price of the given coin
     */
    public double getCurrentPrice() {
        return currentPrice.get();
    }

    /**
     * getter method for the properties of the given
     * coin's price
     * @return current price
     */
    public DoubleProperty currentPriceProperty() {
        return currentPrice;
    }

    /**
     * setter method for the current price of the given
     * coin
     * @param currentPrice updated price of the given coin
     */
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice.set(currentPrice);
    }

    /**
     * Override method of tostring for formatting of the
     * information being passed in from the API
     * @return a formatted string
     */
    @Override
    public String toString(){
        return String.format("%20s: $%-10.2f", this.name, this.currentPrice.getValue());
    }
}
