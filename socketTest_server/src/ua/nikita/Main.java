package ua.nikita;
import java.net.*;

public class Main {
    public static ConnectionWaiter waiter;

    public static void main(String[] args) {

            int port =6666;
            try{
                ServerSocket ss = new ServerSocket(port);
                waiter =  new ConnectionWaiter(ss);
                waiter.start();
            } catch (Exception x) {x.printStackTrace();
        }

    }
}
