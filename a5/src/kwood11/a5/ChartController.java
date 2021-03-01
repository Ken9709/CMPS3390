package kwood11.a5;

import Kwood11.a6.Coin;
import Kwood11.a6.CoinGecko;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
* Chart controller creates and gives functionality to the chart
* scene
*
* @author Kenneth Wood
* @version 1.0
 */
public class ChartController {
    @FXML
    ImageView btnBackArrow;

    @FXML
    LineChart<Number, Number> priceChart;

    @FXML
    ComboBox cbDateRange;

    @FXML
    ComboBox cbCoinSelector;

    Coin bitcoin, ethereum;

    /**
     * Method used to initialize the chart and
     * put current information into it
     */
    public void initialize(){
        priceChart.setAnimated(false);
        bitcoin = new Coin("bitcoin");
        ethereum = new Coin("ethereum");

        CoinGecko.updatePriceHistory(bitcoin,365);
        CoinGecko.updatePriceHistory(ethereum, 365);

        priceChart.getData().add(bitcoin.getHistoricalValues());


    }

    /**
     * Handler for the back arrow in our chart to back to the main
     * scene
     * @param mouseEvent Event triggered by clicking the back arrow
     * @throws IOException
     */
    public void OnBackArrowClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
        Stage primaryStage = (Stage) btnBackArrow.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 700, 475));
    }

    /**
     * Handler for when the date dropdown is adjusted
     * @param actionEvent Event of the new date being selected
     */
    public void onDateRangeChanged(ActionEvent actionEvent) {
        int days= 0;
        switch((String) cbDateRange.getValue()){
            case "Year":
                days = 365;
                break;
            case "90 Days":
                days= 90;
                break;
            case "60 Days":
                days= 60;
                break;
            case "30 Days":
                days = 30;
                break;
            case "Week":
                days= 7;
                break;
        }

        CoinGecko.updatePriceHistory(bitcoin,days);
        CoinGecko.updatePriceHistory(ethereum,days);
        updateChart();
    }

    /**
     * Method to update the chart with new information after
     * different coin and date parameters are chosen
     */
    private void updateChart(){
        priceChart.getData().clear();
        switch((String) cbCoinSelector.getValue()){
            case "Bitcoin":
                priceChart.getData().add(bitcoin.getHistoricalValues());
                break;
            case "Ethereum":
                priceChart.getData().add(ethereum.getHistoricalValues());
                break;
            case "All":
                priceChart.getData().add(bitcoin.getHistoricalValues());
                priceChart.getData().add(ethereum.getHistoricalValues());
                break;

        }

    }

    /**
     * Event handler for when the coin selected is changed
     * on the chart
     * @param actionEvent Event that occurs when the current coin is changed
     */
    public void onCoinSelectionChanged(ActionEvent actionEvent) {
        updateChart();
    }
}
