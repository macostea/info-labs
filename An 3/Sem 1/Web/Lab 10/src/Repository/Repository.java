package Repository;

import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mihaicostea on 02/01/15.
 */
public class Repository {
    public DBManager dbManager = new DBManager();

    public Repository() {

    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            ResultSet set = this.dbManager.getAll();

            while (set.next()) {
                User user = User.userFromResultSet(set);
                users.add(user);
            }

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return users;
        }
    }

    public User getUserWithName(String name) {
        User user = null;
        try {
            ResultSet set = this.dbManager.getUserWithName(name);
            while (set.next()) {
                if (user != null) {
                    return null;
                } else {
                    user = User.userFromResultSet(set);
                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return user;
        }
    }

    public ArrayList<User> getUsersByAge(int age) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            ResultSet set = this.dbManager.getUsersWithAge(age);
            while (set.next()) {
                users.add(User.userFromResultSet(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return users;
        }
    }

    public ArrayList<User> getUsersByOtherFields(String term) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            ResultSet set = this.dbManager.getUsersWithFieldsLike(term);
            while (set.next()) {
                users.add(User.userFromResultSet(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return users;
        }
    }

    public User getUserById(int id) {
        User user = null;
        try {
            ResultSet set = this.dbManager.getUserWithId(id);
            while (set.next()) {
                if (user != null) {
                    return null;
                } else {
                    user = User.userFromResultSet(set);
                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return user;
        }
    }

    public User updateUser(User user) {
        User newUser = null;
        try {
            int result = this.dbManager.updateUser(user.getId(), user.getName(), user.getEmail(), user.getAge(), user.getPictureURL(), user.getTown());
            if (result != 0) {
                newUser = user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return newUser;
        }
    }

    public User addUser(User user) {
        User newUser = null;
        try {
            int result = this.dbManager.addUser(user.getName(), user.getEmail(), user.getAge(), user.getPictureURL(), user.getTown());
            if (result != 0) {
                newUser = user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return newUser;
        }
    }
}
