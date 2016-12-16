package ua.nikita;

import java.net.*;
import java.io.*;
import java.util.Random;

public class Main {

    private static Random rng = new Random();

    public static void main(String[] args) {

        int serverPort = 6666;
        String address = "127.0.0.1";

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("is server = " + address + "and socket =" + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort);
            System.out.println("I guess so");

//            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

//            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String line = null;
            System.out.println("Type something to send");
            System.out.println();

            GettingSomeShit in = new GettingSomeShit(socket);
            in.start();

            while (true) {
                Thread.sleep(rng.nextInt(5) * 1000);

//                line = keyboard.readLine();

                line = generateString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 8);

                System.out.println("Sending...  " + line);

                out.writeUTF(line);

                out.flush();
                System.out.println("Try again");
                System.out.println();
            }

        } catch (Exception x) {
            x.printStackTrace();
        }

    }

    public static String generateString(String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

}
