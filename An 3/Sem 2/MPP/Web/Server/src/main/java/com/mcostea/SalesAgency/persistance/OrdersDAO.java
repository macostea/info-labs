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

    public ArrayList<Order> getOrders() {
        synchronized (this) {
            try {
                this.connect();
                String query = "SELECT orders.id, orders.quantity, orders.status, agents.id AS 'agentId', agents.name AS 'agentName', client.id AS 'clientId', client.name AS 'clientName', client.address AS 'clientAddress', products.id AS 'productId', products.name AS 'productName', products.price AS 'productPrice', products.quantity AS 'productQuantity'\n" +
                        "FROM orders\n" +
                        "JOIN agents ON agents.id=orders.agentId\n" +
                        "JOIN client ON client.id=orders.clientId\n" +
                        "JOIN products ON products.id=orders.productId";

                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                ArrayList<Order> result = new ArrayList<Order>();

                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("id"));
                    order.setQuantity(rs.getInt("quantity"));
                    order.setStatus(rs.getString("status"));

                    Agent agent = new Agent();
                    agent.setAgentID(rs.getInt("agentId"));
                    agent.setName(rs.getString("agentName"));

                    Client client = new Client();
                    client.setClientID(rs.getInt("clientId"));
                    client.setName(rs.getString("clientName"));
                    client.setAddress(rs.getString("clientAddress"));

                    Product product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setName(rs.getString("productName"));
                    product.setQuantity(rs.getInt("productQuantity"));
                    product.setPrice(rs.getInt("productPrice"));

                    order.setAgent(agent);
                    order.setClient(client);
                    order.setProduct(product);

                    result.add(order);
                }

                rs.close();
                this.disconnect();
                return result;

            } catch (SQLException ex) {
                Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
    }

    public boolean updateOrder(Order o) {
        synchronized (this) {
            try {
                this.connect();
                String query = "UPDATE agency.orders SET productId=" + o.getProduct().getProductId() +
                        ", agentId=" + o.getAgent().getAgentID() + ", clientId=" + o.getClient().getClientID() +
                        ", quantity=" + o.getQuantity() + ", status=" + o.getStatus() +
                        "WHERE id=" + o.getOrderId();

                Statement stmt = this.conn.createStatement();
                int res = stmt.executeUpdate(query);

                System.out.println("Update result = " + res);

                this.disconnect();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

    public boolean addOrder(Order o) {
        synchronized (this) {
            try {
                this.connect();
                String query = "INSERT INTO agency.orders VALUES (NULL, " + o.getProduct().getProductId() + ", " + o.getAgent().getAgentID() +
                        ", " + o.getClient().getClientID() + ", " + o.getQuantity() + ", '" + o.getStatus() + "')";

                Logger.getLogger(OrdersDAO.class.getName()).log(Level.INFO, query, (Object[]) null);

                Statement stmt = this.conn.createStatement();
                int res = stmt.executeUpdate(query);

                System.out.println("Insert result = " + res);

                this.disconnect();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

    public boolean removeOrder(Order o) {
        synchronized (this) {
            try {
                this.connect();
                String query = "DELETE FROM agency.orders WHERE id=" + o.getOrderId();

                Statement stmt = this.conn.createStatement();
                int res = stmt.executeUpdate(query);

                System.out.println("Delete result = " + res);

                this.disconnect();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

    public boolean getLoginResponse(Integer userId) {
        synchronized (this) {
            try {
                this.connect();
                String query = "Select * FROM Users WHERE ID ='" + userId + "'";

                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    rs.close();
                    this.disconnect();
                    return true;
                }

                rs.close();
                this.disconnect();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }
}
