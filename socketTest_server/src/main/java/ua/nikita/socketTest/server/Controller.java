package ua.nikita.socketTest.server;
import java.util.Scanner;

/**
 * Created by nikyt on 03.03.2017.
 */
public class Controller extends Thread {
    public static boolean busy = false;
    public static String command;


    public void run(){
        while(true) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            System.out.println(command);
                if (command.equals("exit") ) {
                System.exit(0);
            }
            try{
            Thread.sleep(2000);} catch (java.lang.InterruptedException e){ e.printStackTrace();}
        }
    }


}
