package kwood11.a5;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Main driver application for our coin tracker
 *
 * @author Kenneth Wood
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Overridden start method to create the stage and scene as we want it
     * @param primaryStage Default stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Details.fxml"));
        Parent root = loader.load();
        DetailsController controller = loader.getController();
        Scene scene = new Scene(root, 700, 475);
        primaryStage.setTitle("Coin Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnHidden(e -> controller.shutdown());


    }

    /**
     * Main method that launches the application through the stage above
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
