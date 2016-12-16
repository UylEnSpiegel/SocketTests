package ua.nikita.socketTest.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Nikita.Hatnyuk on 10.12.2016.
 */
public class Transmitter extends Thread {

    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;

    public Transmitter(Socket socket) {
        this.socket = socket;
        try {
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        String line = null;

        while (true) {
            try {
                line = in.readUTF();
                System.out.println("Message is :" + line);
                System.out.println("Returning this crap");

                ConnectionWaiter.getInstance().massEffect("Everyone should got this: " + line);

            } catch (java.io.IOException e) {
                e.printStackTrace();
                break;
            }
            line = null;
        }
    }

    public void sendToClient(String s) {
        try {
            out.writeUTF(s);
            out.flush();
            System.out.println("4sure4sure " + s);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


}
