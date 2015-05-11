package com.mcostea.SalesAgency.controller;

import com.mcostea.SalesAgency.model.Order;
import com.mcostea.SalesAgency.model.Product;
import com.mcostea.SalesAgency.protocol.*;

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

                    if (packet.getClass().equals(OrdersUpdatedPacket.class)) {
                        System.out.println("Update notification received");
                        if (((OrdersUpdatedPacket)packet).getOrders() != null) {
                            this.setChanged();
                        }
                        this.notifyObservers(((OrdersUpdatedPacket)packet).getOrders());
                        this.clearChanged();
                    }

                    if (packet.getClass().equals(ErrorPacket.class)) {
                        System.out.println(((ErrorPacket) packet).getMessage());
                    }

                    if (packet.getClass().equals(GetAllOrdersPacket.class)) {
                        System.out.println("Got a server response back");
                        if (((GetAllOrdersPacket)packet).getOrders() != null) {
                            this.setChanged();
                        }
                        this.notifyObservers(((GetAllOrdersPacket)packet).getOrders());
                        this.clearChanged();
                    }
                }
            }).start();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getOrders() {
        GetAllOrdersPacket packet = new GetAllOrdersPacket();

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
        AddOrderPacket packet = new AddOrderPacket();
        packet.setOrder(o);
        try {
            this.outputStream.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
