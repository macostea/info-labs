package com.company.Model;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class Cake extends Product {
    public String shape;
    public String type;

    @Override
    public String toString() {
        return String.format("Cake, %s, %s, %d", shape, type, price);
    }
}
