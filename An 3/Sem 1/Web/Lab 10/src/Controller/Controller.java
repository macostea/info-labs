package Controller;

import Model.User;
import Repository.Repository;

import java.util.ArrayList;

/**
 * Created by mihaicostea on 02/01/15.
 */
public class Controller {
    private Repository repo = new Repository();

    public ArrayList<User> getAllUsers() {
        return this.repo.getAll();
    }

    public User loginUser(String name) {
        User user = this.repo.getUserWithName(name);
        return user;
    }

    public ArrayList<User> getUsersWithDetails(String details) {
        try {
            int age = Integer.parseInt(details);
            return this.repo.getUsersByAge(age);
        } catch (NumberFormatException ex) {
            return this.repo.getUsersByOtherFields(details);
        }
    }

    public User getUserById(int id) {
        return this.repo.getUserById(id);
    }

    public User updateUser(User user) {
        return this.repo.updateUser(user);
    }

    public User addUser(User user) {
        return this.repo.addUser(user);
    }
}
