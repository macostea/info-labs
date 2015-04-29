package com.mcostea.SalesAgency.servermpp;

import com.mcostea.SalesAgency.model.Order;
import com.mcostea.SalesAgency.persistance.DAO;
import com.mcostea.SalesAgency.persistance.OrdersDAO;
import com.mcostea.SalesAgency.protocol.Packet;
import com.mcostea.SalesAgency.protocol.RequestType;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread {

    Socket socket;
    boolean running;
    InputStream inputStream;
    OutputStream outputStream;
    ObjectInputStream objectInput;
    ObjectOutputStream objectOutput;
    Server server;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;
        running = true;

        initStreams();
    }

    public void initStreams() {
        try {
            outputStream = socket.getOutputStream();
            objectOutput = new ObjectOutputStream(outputStream);
            objectOutput.flush();

            inputStream = socket.getInputStream();
            objectInput = new ObjectInputStream(inputStream);

            this.server.connections.add(objectOutput);
        } catch (IOException ex) {
            System.out.println("Error on socket streams!");
        }
    }

    private void sendAllOrders() {
        ArrayList<Order> orders = this.server.persistance.getOrders();

        Packet packet = new Packet();
        packet.setRequestType(RequestType.SERVER_RESPONSE);
        packet.setOrders(orders);

        System.out.println(orders);

        try {
            objectOutput.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addOrder(Order o) {
        if (this.server.persistance.addOrder(o)) {
            this.server.sendUpdateNotification(this.server.persistance.getOrders());
        } else {
            Packet packet = new Packet();
            packet.setRequestType(RequestType.ERROR);
            try {
                objectOutput.writeObject(packet);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void updateOrder(Order o) {
        if (this.server.persistance.updateOrder(o)) {
            this.server.sendUpdateNotification(this.server.persistance.getOrders());
        } else {
            Packet packet = new Packet();
            packet.setRequestType(RequestType.ERROR);
            try {
                objectOutput.writeObject(packet);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void removeOrder(Order o) {
        if (this.server.persistance.removeOrder(o)) {
            this.server.sendUpdateNotification(this.server.persistance.getOrders());
        } else {
            Packet packet = new Packet();
            packet.setRequestType(RequestType.ERROR);
            try {
                objectOutput.writeObject(packet);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            Packet packet = null;

            try {
                packet = (Packet) objectInput.readObject();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getLocalizedMessage());

                this.server.connections.remove(objectOutput);

                running = false;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (packet == null) {
                continue;
            }

            switch (packet.getRequestType()) {
                case GET_ALL_ORDERS: //SEND ORDERS
                {
                    System.out.println("Client requested all orders");
                    this.sendAllOrders();
                    break;
                }

                case ADD_ORDER: //ADD NEW ORDER
                {
                    System.out.println("Client added a new order");
                    this.addOrder(packet.getOrderToProcess());
                    break;
                }

                case UPDATE_ORDER:
                {
                    System.out.println("Client updated an order");
                    this.updateOrder(packet.getOrderToProcess());
                    break;
                }

                case REMOVE_ORDER:
                {
                    System.out.println("Client removed an order");
                    this.removeOrder(packet.getOrderToProcess());
                }
            }
        }
    }
}
