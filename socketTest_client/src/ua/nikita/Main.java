package ua.nikita;
import java.net.*;
import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int serverPort = 6666;
        String address = "127.0.0.1";

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("is server = " + address + "and socket =" + serverPort + "?");
            Socket socket = new Socket(ipAddress,serverPort);
            System.out.println("I guess so");

//            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

//            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String line = null;
            System.out.println("Type something to send");
            System.out.println();

            GettingSomeShit in = new GettingSomeShit();
            in.params(socket);
            in.start();

            while(true){
//                line = keyboard.readLine();

                Random rng = new Random();

                line  = generateString(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" , 8);
                System.out.println("Sending...");

                // задержка в 2 сек меж отправками
                try {
//                    Thread.sleep(2000);
                    Thread.sleep(1);
                    out.writeUTF(line);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                out.flush();
//                line = in.readUTF();
//                System.out.println("Server sent :" + line);
                System.out.println("Try again");
                System.out.println();

            }


        } catch (Exception x){
            x.printStackTrace();
        }

    }

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

}
