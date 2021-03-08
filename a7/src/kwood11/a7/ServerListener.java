package kwood11.a7;

import jforsythe.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;


/**
 * Thread listener class to get messages from the server
 * and display them in the chat client
 */
public class ServerListener extends Thread{
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    Controller controller;

    /**
     * Server listener object
     * @param socket socket that we're connecting to the server on
     * @param controller our programs controller
     * @throws IOException
     */
    public ServerListener(Socket socket, Controller controller) throws IOException{
        this.socket = socket;
        this.controller = controller;
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
    }

    /**
     * Overriden run method for the thread
     */
    @Override
    public void run() {
        try{
            while(true){
                Message tmp = (Message) objectInputStream.readObject();
                controller.addMessage(String.format("%s: %s%n", tmp.getName(), tmp.getMessage()));
            }
        } catch(ClassNotFoundException | IOException e){
            System.err.println("Disconnected From Server");
        } finally{
            try {
                objectInputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
