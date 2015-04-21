package com.mcostea.SalesAgency.servermpp;

import com.mcostea.SalesAgency.view.ServerView;

import java.io.IOException;

/**
 *
 * @author Maier Mircea
 */
public class ServerListener extends Thread {

    public ServerView parent;

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
