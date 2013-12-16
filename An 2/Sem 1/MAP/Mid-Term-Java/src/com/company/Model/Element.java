package com.company.Model;

import java.io.Serializable;

public class Element implements HasId, Serializable {
    public int id;
    public String color;

    public Element(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s | %d | %s", this.getClass().toString(), this.id, this.color);
    }
}
