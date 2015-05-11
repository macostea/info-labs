package com.mcostea.SalesAgency.servermpp;

import com.mcostea.SalesAgency.view.ServerView;
import org.glassfish.tyrus.server.Server;

import java.io.IOException;

public class ServerListener extends Thread {

    public ServerView parent;

    @Override
    public void run() {
        Server server = new Server("localhost", 4322, "/", null, OrdersServerEndpoint.class);

        try {
            server.start();
        } catch (Exception ex) {
            System.out.println("OrdersServerEndpoint run failed!");
            ex.printStackTrace();
        }
    }
}
