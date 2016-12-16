package ua.nikita.socketTest.server;


public class Main {


    public static void main(String[] args) {
        try {
            ConnectionWaiter.getInstance().start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
