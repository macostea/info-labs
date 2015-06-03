package protocol;

import model.User;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class LoginPacket extends Packet {
    private User user;
    private boolean loggedIn;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
