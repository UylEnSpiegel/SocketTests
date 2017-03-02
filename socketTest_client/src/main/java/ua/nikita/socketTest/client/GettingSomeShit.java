package ua.nikita.socketTest.client;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Nikita.Hatnyuk on 10.12.2016.
 */
public class GettingSomeShit extends Thread {
    private DataInputStream in;

    public GettingSomeShit(Socket socket) {
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String line = null;
        while (true) {
            try {
                line = in.readUTF();
                System.out.println(line);
            } catch (java.io.IOException e) {
                e.printStackTrace();
                break;
            }
        }

    }
}
