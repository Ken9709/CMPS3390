package Kwood11.a8;

import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import jforsythe.Message;


/**
 * Server listener class that attaches to the server socket and creates
 * an input stream
 */
public class ServerListener extends Thread{
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private EditText output;
    public boolean running = true;


    /**
     * The server listener object
     * @param socket socket we're listening on
     * @param output Output of the server
     */
    public ServerListener(Socket socket, EditText output){
        this.socket = socket;
        this.output = output;
        try{
            inputStream  = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Override of the run method to check for messages while running
     */
    @Override
    public void run (){
        try{
            while(running){
                Message tmp = (Message)objectInputStream.readObject();
                output.append(String.format("%s: %s %n", tmp.getName(), tmp.getMessage()));
            }
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}