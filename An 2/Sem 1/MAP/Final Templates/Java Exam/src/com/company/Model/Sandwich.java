package com.company.Model;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class Sandwich extends Product {
    public int weight;
    public String content;

    @Override
    public String toString() {
        return String.format("Sandwich, %d, %s, %s, %d", weight, content, servingType, price);
    }
}
