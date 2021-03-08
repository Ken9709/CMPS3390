package kwood11.a7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main driver application
 * @author Kenneth Wood
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Method to start the GUI
     * @param primaryStage our main chat interface
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage)throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
        Parent root = loader.load();
        Controller controller =loader.getController();
        Scene scene = new Scene(root, 600,400);
        primaryStage.setTitle("CMPS 3390");
        primaryStage.setScene(scene);
        primaryStage.setOnHiding(e-> {
            try {
                controller.exit();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        primaryStage.show();
    }

    /**
     * Main method to launch the chat GUI
     * @param args Unsure of how to describe the arguements to this?
     */
    public static void main(String[] args) {
        launch(args);
    }
}
