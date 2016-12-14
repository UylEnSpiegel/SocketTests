package ua.nikita;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita.Hatnyuk on 10.12.2016.
 */
public class ConnectionWaiter extends Thread {

    private List<Transmitter> Connections = new ArrayList<>();

    private ServerSocket ss;

    public ConnectionWaiter(ServerSocket ss) {
        this.ss = ss;

    }

    public void run() {

        while (true) {
            try {
                Socket socket = ss.accept();

                Transmitter client = new Transmitter(socket);
                Connections.add(client);
                client.start();
//               Connections.get(Connections.indexOf(client)).start();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void MassEffect(String s) {
        for (Transmitter client : Connections) {
            client.sendToClient(s);
        }
    }


}
