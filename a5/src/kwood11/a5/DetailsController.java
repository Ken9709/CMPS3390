package kwood11.a5;

import Kwood11.a6.Coin;
import Kwood11.a6.UpdateCoinTimerTask;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class that controls the main page with coins
 * and their given values updated in real time
 */
public class DetailsController {
    @FXML
    Label labETHValue;
    @FXML
    Label labBTCValue;

    @FXML
    HBox btcHBox;

    @FXML
    HBox ethHBox;

    Coin bitcoin, ethereum;
    Timer bitcoinTimer, ethereumTimer;

    /**
     * Method to initialize the coins to their current values
     * when the application is loaded
     */
    public void initialize(){
        this.bitcoin = new Coin("bitcoin");
        this.ethereum = new Coin("ethereum");


        labBTCValue.textProperty().bind(Bindings.format("$%-10.2f", bitcoin.currentPriceProperty()));
        labETHValue.textProperty().bind(Bindings.format("$%-10.2f", ethereum.currentPriceProperty()));

        bitcoinTimer = new Timer();
        bitcoinTimer.schedule(new TimerTask() {



            @Override
            public void run() {
                Platform.runLater(new UpdateCoinTimerTask(bitcoin));
            }
        }, 0, 5000);

        ethereumTimer = new Timer();
        ethereumTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new UpdateCoinTimerTask(ethereum));
            }
        }, 0, 5000);
    }

    /**
     * Event handler for when one of the coins is clicked to take
     * the user to the chart
     * @param mouseEvent Event when either of the two coins is clicked
     * @throws IOException
     */
    public void onDetailButtonClicked(MouseEvent mouseEvent) throws IOException {
        shutdown();

            System.out.println("Change to BTC Scene");
            Parent root = FXMLLoader.load(getClass().getResource("Chart.fxml"));
            Stage primaryStage = (Stage) btcHBox.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 700, 475));
        }

    /**
     * Shutdown method for the timers so that they stop
     * running with the application is closed
      */
    public void shutdown(){
        System.out.println("Shutdown was called Stopping Timers");
        bitcoinTimer.cancel();
        ethereumTimer.cancel();
    }
}