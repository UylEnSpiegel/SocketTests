package ua.nikita.socketTest.client;

import java.net.*;
import java.io.*;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    static Logger log = LogManager.getLogger(Main.class.getName());

    private static Random rng = new Random();

    public static void main(String[] args) {

        String username;

        int serverPort = 6666;
        log.debug("Server socket port is" + serverPort);

        String address = "127.0.0.1";
        log.debug("Server ip address = " + address);

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

            username = generateString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 8);
            out.writeUTF(username);
            out.flush();
            log.debug("Username sent to server. Username is " + username);
            System.out.println("Username sent: "+username);

            while (true) {
                Thread.sleep(rng.nextInt(5) * 1000);

//                line = keyboard.readLine();

                line = generateString("abcdefghijklmnopqrstuvwxyz", 8);
                log.debug("generating string to send. strng is = " + line);
                System.out.println("Sending...  : " + username + " " + line);

                out.writeUTF(line);

                out.flush();
                log.error("String sended succesfuly");
                System.out.println("Try again");
                System.out.println();
            }

        } catch (Exception x) {
            log.error("something went really really wrong, bitch you gotta do some thing about it" + x);
            x.printStackTrace();
        }

    }

    public static String generateString(String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        log.debug("string generator generated string = " + text);
        return new String(text);
    }

}
