package persistance;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class UsersDAO extends DAO {
    public User loginUser(User user) {
        synchronized (this) {
            try {
                this.connect();
                String query = "SELECT * FROM Users WHERE userName='" + user.getUserName() + "' AND password='" + user.getPassword() + "'";
                Statement statement = this.conn.createStatement();
                ResultSet rs = statement.executeQuery(query);

                if (rs.next()) {
                    User u = new User();
                    u.setUserName(rs.getString("userName"));
                    u.setPassword(rs.getString("password"));
                    u.setCheckpoint(rs.getInt("checkpoint"));

                    return u;
                }

                rs.close();
                this.disconnect();

                return null;
            } catch (SQLException ex) {
                Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
    }
}
