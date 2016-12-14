package ua.nikita;
import java.net.*;

public class Main {


    public static void main(String[] args) {


            try{
//                ServerSocket ss = new ServerSocket(port);
//                waiter =  new ConnectionWaiter(ss);
                ConnectionWaiter waiter = ConnectionWaiter.getInstance();
                waiter.start();
            } catch (Exception x) {x.printStackTrace();
        }

    }
}
