package com.company.Repository;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by C.Mihai on 14/03/14.
 */
public class DBConnection{
    private static Logger logger = Logger.getLogger("Students");
    public DBConnection() {
        super();
    }

    public Connection getConnection() throws SQLException {
        logger.info("[Entering:] Repository.getConnection");
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");

        String urlString = "jdbc:mysql://127.0.0.1:3306/Students";
        conn = DriverManager.getConnection(urlString, connectionProps);

        return conn;
    }

    public static ResultSet getTable(Connection connection, String table) throws SQLException {
        logger.info("[Entering:] Repository.getTable");
        String query = "SELECT * FROM " + table;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
