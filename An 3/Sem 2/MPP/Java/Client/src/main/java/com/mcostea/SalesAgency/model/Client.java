package com.mcostea.SalesAgency.model;

import java.io.Serializable;

/**
 * Created by mihaicostea on 16/04/15.
 */
public class Client implements Serializable {
    private int clientID;
    private String name;
    private String address;

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
