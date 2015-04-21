package com.mcostea.SalesAgency.model;

import java.io.Serializable;

/**
 * Created by mihaicostea on 16/04/15.
 */
public class Agent implements Serializable {
    private int agentID;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }
}
