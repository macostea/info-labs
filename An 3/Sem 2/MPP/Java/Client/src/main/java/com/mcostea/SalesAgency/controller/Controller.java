package com.mcostea.SalesAgency.controller;

import com.mcostea.SalesAgency.protocol.Packet;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihaicostea on 21/04/15.
 */
public class Controller {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Controller() {
        try {
            this.socket = new Socket("localhost", 4322);
            this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.inputStream = new ObjectInputStream(this.socket.getInputStream());

            new Thread(() -> {
                boolean running = true;
                while (running) {
                    Packet packet = null;

                    try {
                        packet = (Packet) this.inputStream.readObject();
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getLocalizedMessage());
                        running = false;
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (packet == null) {
                        continue;
                    }

                    switch (packet.getRequestType()) {
                        case UPDATE_NOTIFICATION: //Update received
                        {
                            System.out.println("Update notification received");
                            // TODO: Respond appropriately
                            break;
                        }

                        case ERROR: //Server error
                        {
                            System.out.println("Server returned an error");
                            break;
                        }

                        case SERVER_RESPONSE: {
                            System.out.println("Got a server response back");
                            // TODO: Respond appropriately
                            break;
                        }
                    }
                }
            }).start();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
