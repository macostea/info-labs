package server;

import java.io.IOException;

public class ServerListener extends Thread {

    @Override
    public void run() {
        Server server = new Server();

        try {
            server.run();
        } catch (IOException ex) {
            System.out.println("Server run failed!");
        }
    }
}
