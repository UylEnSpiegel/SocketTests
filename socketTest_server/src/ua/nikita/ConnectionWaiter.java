package ua.nikita;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita.Hatnyuk on 10.12.2016.
 */
public class ConnectionWaiter extends Thread {

    private final int port = 6666;

    private static ConnectionWaiter instance;

    private ServerSocket serverSocket;
    private List<Transmitter> clientList = new ArrayList<>();


    private ConnectionWaiter() throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void run() {

        while (true) {
            try {
                Transmitter client = new Transmitter(serverSocket.accept());
                clientList.add(client);
                client.start();
//               clientList.get(clientList.indexOf(client)).start();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void massEffect(String s) {
        for (Transmitter client : clientList) {
            client.sendToClient(s);
            System.out.println("Sent everything4sure");
        }
    }


    public static ConnectionWaiter getInstance() throws IOException {
        if (instance == null) instance = new ConnectionWaiter();
        return instance;
    }


}
