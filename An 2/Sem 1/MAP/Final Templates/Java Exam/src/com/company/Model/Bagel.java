package com.company.Model;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class Bagel extends Product {
    public int weight;
    public String type;
    public int price;

    @Override
    public void readAttributesFromString(String string) {
        String[] tokens = string.split("[,]+");

    }
}
