package View;

import Model.Contact;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Client {
    private Socket socket;
    private Thread thread;
    private int port;
    private boolean foundHost = false;
    private ObjectOutputStream objectOutputStream = null;
    private ObjectInputStream objectInputStream = null;
    private HashMap<String, Contact> contacts;
    private UI ui;

    public Client(int port, HashMap<String,Contact> contacts, UI ui){
        this.contacts = contacts;
        this.ui = ui;
        this.port = port;
        connectAsClient(this.port);
    }
    private void connectAsClient(Integer port) {
        while (!this.foundHost) {// cat timp nu a gasit server, incearca sa se conecteze pana gaseste
            try {
                socket = new Socket("127.0.0.1", port);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.flush(); //curata stream ul conform documentatiei
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                foundHost = true;
                System.out.println("CONNECTED TO SERVER");
            } catch (Exception e) {
                System.out.println("Couldnt connect to server...");
                foundHost = false;
            }
        }
    }
    public void sendObject(Object object) {
        if (foundHost) {
            try {
                this.objectOutputStream.writeObject(object);
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }
}
