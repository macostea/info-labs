package model;

import java.io.Serializable;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class User implements Serializable {
    private int checkpoint;
    private String userName;
    private String password;

    public int getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
