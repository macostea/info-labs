package Repository;

import java.sql.*;

/**
 * Created by mihaicostea on 02/01/15.
 */
public class DBManager {
    private Connection connection;

    public DBManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet getAll() throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery("SELECT * FROM users.users");
    }

    public ResultSet getUserWithName(String name) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery("SELECT * FROM users.users WHERE name = '" + name + "'");
    }

    public ResultSet getUsersWithAge(int age) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery("SELECT * FROM users.users WHERE age = " + age);
    }

    public ResultSet getUsersWithFieldsLike(String term) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM users.users WHERE name LIKE ? OR email LIKE ? OR town LIKE ?");
        statement.setString(1, "%" + term + "%");
        statement.setString(2, "%" + term + "%");
        statement.setString(3, "%" + term + "%");

        return statement.executeQuery();
    }

    public ResultSet getUserWithId(int id) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery("SELECT * FROM users.users WHERE id = " + id);
    }

    public int updateUser(int id, String name, String email, int age, String pictureURL, String town) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("UPDATE users SET id = ?, name = ?, pictureURL = ?, email = ?, age = ?, town = ? where id = ?");
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setString(3, pictureURL);
        statement.setString(4, email);
        statement.setInt(5, age);
        statement.setString(6, town);
        statement.setInt(7, id);
        return statement.executeUpdate();
    }
}
