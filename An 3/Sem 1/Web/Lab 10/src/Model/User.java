package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mihaicostea on 02/01/15.
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String pictureURL;
    private int age;
    private String town;

    public User(int id) {
        this.id = id;
    }

    public static User userFromResultSet(ResultSet set) throws SQLException {
        int id = set.getInt("id");
        User user = new User(id);
        user.setName(set.getString("name"));
        user.setAge(set.getInt("age"));
        user.setEmail(set.getString("email"));
        user.setPictureURL(set.getString("pictureURL"));
        user.setTown(set.getString("town"));

        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getId() {
        return id;
    }
}
