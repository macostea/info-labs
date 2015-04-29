package com.mcostea.SalesAgency.controller;

import com.mcostea.SalesAgency.model.Order;
import com.mcostea.SalesAgency.model.Product;
import com.mcostea.SalesAgency.protocol.Packet;
import com.mcostea.SalesAgency.protocol.RequestType;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihaicostea on 21/04/15.
 */
public class Controller extends Observable {
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
                            if (packet.getOrders() != null) {
                                this.setChanged();
                            }
                            this.notifyObservers(packet.getOrders());
                            this.clearChanged();
                            break;
                        }

                        case ERROR: //Server error
                        {
                            System.out.println("Server returned an error");
                            break;
                        }

                        case SERVER_RESPONSE: {
                            System.out.println("Got a server response back");
                            if (packet.getOrders() != null) {
                                this.setChanged();
                            }
                            this.notifyObservers(packet.getOrders());
                            this.clearChanged();
                            break;
                        }
                    }
                }
            }).start();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getOrders() {
        Packet packet = new Packet();
        packet.setRequestType(RequestType.GET_ALL_ORDERS);

        try {
            this.outputStream.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendProduct(Product p) {
        Packet packet = new Packet();
    }

    public void sendOrder(Order o) {
        Packet packet = new Packet();
        packet.setRequestType(RequestType.ADD_ORDER);
        packet.setOrderToProcess(o);
        try {
            this.outputStream.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
