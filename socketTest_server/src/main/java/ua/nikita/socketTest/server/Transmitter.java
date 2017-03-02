package ua.nikita.socketTest.server;

import ua.nikita.socketTest.server.FIlesBitch.StaticHistoryTest;

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
    private String userName;

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
        try {
            line = in.readUTF();
            userName = line;
            ConnectionWaiter.getInstance().addUsername(userName,this);
        }catch (java.io.IOException e){e.printStackTrace();}

        for (int i = 1; i < StaticHistoryTest.historyLinesList.size();i++){
            sendToClient(StaticHistoryTest.historyLinesList.get(i));
        }

        while (true) {
            try {
                line = in.readUTF();
                System.out.println("Message is :" + line);
                System.out.println("Returning this crap");

                ConnectionWaiter.getInstance().massEffect(userName + " said to everyone: " + line);

            } catch (java.io.IOException e) {
                e.printStackTrace();
                try {
                    ConnectionWaiter.getInstance().RemoveBitch(this);
                } catch (java.io.IOException u){
                    u.printStackTrace();
                }
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
