package com.mcostea.SalesAgency.servermpp;

import com.mcostea.SalesAgency.protocol.Packet;
import com.mcostea.SalesAgency.protocol.RequestType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket serverSocket;
    public volatile ArrayList<ObjectOutputStream> connections = new ArrayList<ObjectOutputStream>();
    private volatile ArrayList<ClientHandler> workers = new ArrayList<ClientHandler>();

    public void run() throws IOException {

        int port = 4322;

        serverSocket = new ServerSocket(port);

        while (true) {
            Socket newConnection = serverSocket.accept();

            System.out.println("New client CONNECTED!");

            if (newConnection != null) {
                ClientHandler handler = new ClientHandler(newConnection, this);
                workers.add(handler);
                handler.start();
            }
        }
    }

    public void sendUpdateNotification() {
        synchronized (this) {
            for (ObjectOutputStream ou : this.connections) {
                Packet packet = new Packet();
                packet.setRequestType(RequestType.UPDATE_NOTIFICATION);

                try {
                    ou.writeObject(packet);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
