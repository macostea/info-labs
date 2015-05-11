package com.mcostea.SalesAgency.servermpp;

import com.cedarsoftware.util.io.JsonObject;
import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import com.mcostea.SalesAgency.model.Agent;
import com.mcostea.SalesAgency.model.Client;
import com.mcostea.SalesAgency.model.Order;
import com.mcostea.SalesAgency.model.Product;
import com.mcostea.SalesAgency.protocol.*;
import jdk.nashorn.internal.ir.debug.JSONWriter;

import javax.xml.bind.util.JAXBSource;
import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread {

    Socket socket;
    boolean running;
    InputStream inputStream;
    OutputStream outputStream;
    Server server;
    BufferedReader br;
    PrintWriter printWriter;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;
        running = true;

        initStreams();
    }

    public void initStreams() {
        try {
            outputStream = socket.getOutputStream();

            inputStream = socket.getInputStream();

            this.br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            this.printWriter = new PrintWriter(new OutputStreamWriter(outputStream, Charset.forName("UTF-8")));

            this.server.connections.add(outputStream);
        } catch (IOException ex) {
            System.out.println("Error on socket streams!");
        }
    }

    private void sendAllOrders() {
        ArrayList<Order> orders = this.server.persistance.getOrders();

        Packet packet = new Packet();
        packet.setPacketType("getAllOrders");
        packet.setOrders(orders);

        System.out.println(orders);

        try {
            String json = JsonWriter.objectToJson(packet);
            this.printWriter.write(json);
            this.printWriter.write("\n");
            this.printWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addOrder(Order o) {
        if (this.server.persistance.addOrder(o)) {
            this.server.sendUpdateNotification(this.server.persistance.getOrders());
        } else {
            Packet packet = new Packet();
            packet.setPacketType("error");
            packet.setMessage("Error processing request");
            try {
                outputStream.write(JsonWriter.objectToJson(packet).getBytes(Charset.forName("UTF-8")));
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
            packet.setPacketType("error");
            packet.setMessage("Error processing request");
            try {
                outputStream.write(JsonWriter.objectToJson(packet).getBytes(Charset.forName("UTF-8")));
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
            packet.setPacketType("error");
            packet.setMessage("Error processing request");
            try {
                outputStream.write(JsonWriter.objectToJson(packet).getBytes(Charset.forName("UTF-8")));
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
                String object = br.readLine();
                System.out.println("Got object " + object.substring(1));
                if (!object.startsWith("{")) {
                    object = object.substring(1);
                }
                JsonObject<String, Object> map = (JsonObject<String, Object>)JsonReader.jsonToJava(object);
                packet = new Packet();
                packet.setPacketType((String)map.get("packetType"));

                if (packet.getPacketType().equals("addOrder")) {
                    Order o = new Order();
                    Agent a = new Agent();
                    Product p = new Product();
                    Client c = new Client();

                    JsonObject<String, Object> orderToProcess = (JsonObject<String, Object>)map.get("orderToProcess");
                    o.setOrderId(((Long) orderToProcess.get("OrderId")).intValue());
                    o.setQuantity(((Long) orderToProcess.get("Quantity")).intValue());
                    o.setStatus((String) orderToProcess.get("Status"));

                    JsonObject<String, Object> productToProcess = (JsonObject<String, Object>)orderToProcess.get("Product");
                    p.setProductId(((Long) productToProcess.get("ProductId")).intValue());

                    JsonObject<String, Object> agentToProcess = (JsonObject<String, Object>)orderToProcess.get("Agent");
                    a.setAgentID(((Long) agentToProcess.get("AgentID")).intValue());

                    JsonObject<String, Object> clientToProcess = (JsonObject<String, Object>)orderToProcess.get("Client");
                    c.setClientID(((Long)clientToProcess.get("ClientID")).intValue());

                    o.setAgent(a);
                    o.setProduct(p);
                    o.setClient(c);

                    packet.setOrderToProcess(o);
                }

            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getLocalizedMessage());

                this.server.connections.remove(this.outputStream);

                running = false;
            }

            if (packet == null) {
                continue;
            }

            if (packet.getPacketType().equals("getAllOrders")) {
                System.out.println("Client requested all orders");
                this.sendAllOrders();
            }

            if (packet.getPacketType().equals("addOrder")) {
                System.out.println("Client added a new order");
                this.addOrder(packet.getOrderToProcess());
            }

            if (packet.getPacketType().equals("updateOrder")) {
                System.out.println("Client updated an order");
                this.updateOrder(packet.getOrderToProcess());
            }

            if (packet.getPacketType().equals("removeOrder")) {
                System.out.println("Client removed an order");
                this.removeOrder(packet.getOrderToProcess());
            }
        }
    }
}
