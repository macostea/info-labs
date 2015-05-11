package com.mcostea.SalesAgency.servermpp;

import com.cedarsoftware.util.io.JsonWriter;
import com.mcostea.SalesAgency.model.Order;
import com.mcostea.SalesAgency.persistance.OrdersDAO;
import com.mcostea.SalesAgency.protocol.Packet;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@javax.websocket.server.ServerEndpoint("/orders")
public class OrdersServerEndpoint {

    private static Set<Session> sessionSet;

    public OrdersDAO persistance = new OrdersDAO();

    public void run() throws IOException {

    }

    @OnOpen
    public void getOrders(Session session) {
        this.sessionSet = session.getOpenSessions();

        this.sendUpdateNotification(this.persistance.getOrders());
    }

    public void sendUpdateNotification(ArrayList<Order> orders) {
        synchronized (this) {
            for (Session session : this.sessionSet) {
                Packet packet = new Packet();
                packet.setPacketType("ordersUpdated");
                packet.setOrders(orders);
                try {
                    String json = JsonWriter.objectToJson(packet);
                    session.getBasicRemote().sendText(json);


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
