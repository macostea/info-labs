package com.mcostea.SalesAgency.persistance;

import com.mcostea.SalesAgency.model.Agent;
import com.mcostea.SalesAgency.model.Client;
import com.mcostea.SalesAgency.model.Order;
import com.mcostea.SalesAgency.model.Product;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.Attributes;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersDAO extends DAO {

    private static int portNumber = 4322;


    public static ArrayList<Order> getOrders() {
        try {
            OrdersDAO.connect();
            String query = "SELECT * FROM Orders";

            Statement stmt = OrdersDAO.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Order> result = new ArrayList<Order>();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setStatus(rs.getString("status"));

                Agent agent = new Agent();
                agent.setAgentID(rs.getInt("agentId"));

                Client client = new Client();
                client.setClientID(rs.getInt("clientId"));

                Product product = new Product();
                product.setProductId(rs.getInt("productId"));

                result.add(order);
            }

            rs.close();
            OrdersDAO.disconnect();
            return result;

        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static boolean updateOrder(Order o) {
        try {
            OrdersDAO.connect();
            String query = "UPDATE agency.orders SET productId=" + o.getProduct().getProductId() +
                    ", agentId=" + o.getAgent().getAgentID() + ", clientId=" + o.getClient().getClientID() +
                    ", quantity=" + o.getQuantity() + ", status=" + o.getStatus() +
                    "WHERE id=" + o.getOrderId();

            Statement stmt = OrdersDAO.conn.createStatement();
            int res = stmt.executeUpdate(query);

            System.out.println("Update result = " + res);

            OrdersDAO.disconnect();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean addOrder(Order o) {
        try {
            OrdersDAO.connect();
            String query = "INSERT INTO agency.orders VALUES (NULL, " + o.getProduct().getProductId() + ", " + o.getAgent().getAgentID() +
                    ", " + o.getClient().getClientID() + ", " + o.getQuantity() + ", " + o.getStatus() + ")";

            Statement stmt = OrdersDAO.conn.createStatement();
            int res = stmt.executeUpdate(query);

            System.out.println("Insert result = " + res);

            OrdersDAO.disconnect();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean removeOrder(Order o) {
        try {
            OrdersDAO.connect();
            String query = "DELETE FROM agency.orders WHERE id=" + o.getOrderId();

            Statement stmt = OrdersDAO.conn.createStatement();
            int res = stmt.executeUpdate(query);

            System.out.println("Delete result = " + res);

            OrdersDAO.disconnect();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean getLoginResponse(Integer userId) {
        try {
            OrdersDAO.connect();
            String query = "Select * FROM Users WHERE ID ='" + userId + "'";

            Statement stmt = OrdersDAO.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                rs.close();
                OrdersDAO.disconnect();
                return true;
            }

            rs.close();
            OrdersDAO.disconnect();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static int getPortNumber() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean portNumberExists = false;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("PortNumber")) {
                        portNumberExists = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (portNumberExists) {
                        String portNumberString = new String(ch, start, length);
                        portNumber = Integer.parseInt(portNumberString);
                    }
                }
            };

            saxParser.parse("Config.xml", handler);

        } catch (Exception e) {
            System.out.println(e);
        }

        return portNumber;
    }
}
