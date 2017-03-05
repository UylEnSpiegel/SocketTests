package ua.nikita.socketTest.server;



import java.io.File;
import java.nio.*;

import java.nio.file.Paths;

public class Main {


    public static void main(String[] args) {
//        String workingDirectory = System.getProperty("user.dir");
//        System.out.println(workingDirectory);
       Controller controller = new Controller();
       controller.start();
        try {
            History.getInstance().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ConnectionWaiter.getInstance().start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
