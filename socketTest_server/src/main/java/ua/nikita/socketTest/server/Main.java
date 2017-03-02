package ua.nikita.socketTest.server;


import ua.nikita.socketTest.server.FIlesBitch.StaticHistoryTest;

import java.io.File;
import java.nio.*;

import java.nio.file.Paths;

public class Main {


    public static void main(String[] args) {
//        String workingDirectory = System.getProperty("user.dir");
//        System.out.println(workingDirectory);
        StaticHistoryTest.main();
        try {
            ConnectionWaiter.getInstance().start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
