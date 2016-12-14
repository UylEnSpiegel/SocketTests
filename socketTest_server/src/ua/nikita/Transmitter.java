package ua.nikita;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import ua.nikita.Main.*;

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
        }catch (java.io.IOException e){
            e.printStackTrace();
        }

    }

    public void run () {
        String line = null;
        String line1 = null;
        while(true){
            try {
                line = in.readUTF();
                System.out.println("Message is :" + line);
                System.out.println("Returning this crap");

                line1="Everyone should got this: " + line;
//                Main.waiter.MassEffect(line1);
                ConnectionWaiter waiter = ConnectionWaiter.getInstance();
                waiter.MassEffect(line1);

//                out.writeUTF("NO you are " + line);
//                out.flush();
//                System.out.println("Waiting huenting");
//                System.out.println();
            }catch (java.io.IOException e){
                e.printStackTrace();
                break;
            }




        }


    }

    public void sendToClient (String s){
        try {
            out.writeUTF(s);
            out.flush();
            System.out.println("4sure4sure " + s);
        }catch (java.io.IOException e){e.printStackTrace();}
    }




}
