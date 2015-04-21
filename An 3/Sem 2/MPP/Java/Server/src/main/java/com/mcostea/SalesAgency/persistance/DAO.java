package com.mcostea.SalesAgency.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihaicostea on 16/04/15.
 */
public class DAO {
    protected static String dbConnString = "jdbc:mysql://localhost/agency?user=root";
    protected static String dbDriverName = "com.mysql.jdbc.Connection";

    protected static volatile Connection conn;

    public static void connect() {
        try {
            Class.forName(OrdersDAO.dbDriverName);
            OrdersDAO.conn = DriverManager.getConnection(OrdersDAO.dbConnString);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void disconnect() {
        try {
            OrdersDAO.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
