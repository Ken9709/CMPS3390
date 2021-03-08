package kwood11.a7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import jforsythe.Message;
import jforsythe.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * Controller class for our chat GUI
 */
public class Controller {
    @FXML
    TextField txtInput;

    @FXML
    TextArea txtOutput;

    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;

    private String name;

    /**
     * Method to initialize the user into the chat room by
     * prompting for their name and then entering the server
     * @throws IOException
     */
    public void initialize() throws IOException {
        TextInputDialog nameInput = new TextInputDialog("What is your name?");
        nameInput.setHeaderText("welcome to CMPS 3390 chat");
        nameInput.showAndWait();
        name = nameInput.getResult();

        socket = new Socket("odin.cs.csub.edu", 3390);

        outputStream= socket.getOutputStream();
        outputStream.flush();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.flush();

        ServerListener serverListener= new ServerListener(this.socket,this);
        serverListener.start();

        Message tmp = new Message(MessageType.CONNECT, name, "hello");
        objectOutputStream.writeObject(tmp);
        objectOutputStream.flush();
    }


    /**
     * Handler for when the user types a message into the chat room
     * @param actionEvent When enter is pressed with a message in the chat box
     * @throws IOException
     */
    public void onInputAction(ActionEvent actionEvent) throws IOException {
        Message tmp = new Message(MessageType.MESSAGE, name, txtInput.getText());
        txtInput.clear();
        objectOutputStream.writeObject(tmp);
        objectOutputStream.flush();
    }

    /**
     * Exit method to cleanly close the connection with the server
     * when the user leaves the chat room
     * @throws IOException
     */
    public void exit() throws IOException {
        objectOutputStream.close();
        outputStream.close();
        socket.close();
    }

    /**
     * Method to append a message to the text output
     * @param msg The message that was typed by the user
     */
    public void addMessage(String msg) {
        txtOutput.appendText(msg);
    }
}
