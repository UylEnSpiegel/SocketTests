package ua.nikita;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Nikita.Hatnyuk on 10.12.2016.
 */
public class GettingSomeShit extends Thread {
    private InputStream sin;
    private DataInputStream in;

    public void run (){
        String line = null;
        while (true){
            try{
                line = in.readUTF();
                System.out.println("Server sent :" + line);
            }catch (java.io.IOException e){
                e.printStackTrace();
                break;
            }
        }

    }

    public void params(Socket socket){
        try {
            sin = socket.getInputStream();
            in = new DataInputStream(sin);


        }catch (java.io.IOException e){e.printStackTrace();}
    }

}
