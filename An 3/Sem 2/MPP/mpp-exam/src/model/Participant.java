package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class Participant implements Serializable {
    private int id;
    private String name;
    private int lastCheckpoint;
    private Time timeReached;
    private Time startingTime;

    public Time getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Time startingTime) {
        this.startingTime = startingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLastCheckpoint() {
        return lastCheckpoint;
    }

    public void setLastCheckpoint(int lastCheckpoint) {
        this.lastCheckpoint = lastCheckpoint;
    }

    public Time getTimeReached() {
        return timeReached;
    }

    public void setTimeReached(Time timeReached) {
        this.timeReached = timeReached;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
