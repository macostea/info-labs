package com.mcostea.SalesAgency.servermpp;

import com.mcostea.SalesAgency.model.Order;
import com.mcostea.SalesAgency.persistance.DAO;
import com.mcostea.SalesAgency.persistance.OrdersDAO;
import com.mcostea.SalesAgency.protocol.OrdersUpdatedPacket;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
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

    public OrdersDAO persistance = new OrdersDAO();

    public void run() throws IOException {

        int port = this.getPortNumber();

        if (port == -1) {
            port = 4322;
        }

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

    public void sendUpdateNotification(ArrayList<Order> orders) {
        synchronized (this) {
            for (ObjectOutputStream ou : this.connections) {
                OrdersUpdatedPacket packet = new OrdersUpdatedPacket();
                packet.setOrders(orders);
                try {
                    ou.writeObject(packet);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getPortNumber() {
        final int[] portNumber = {-1};

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean portNumberExists = false;

                @Override
                public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("port")) {
                        portNumberExists = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("port")) {
                        portNumberExists = false;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (portNumberExists) {
                        String portNumberString = new String(ch, start, length);
                        portNumber[0] = Integer.parseInt(portNumberString);
                    }
                }
            };

            saxParser.parse("config.xml", handler);

        } catch (Exception ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.INFO, null, ex);
        }

        return portNumber[0];
    }
}
