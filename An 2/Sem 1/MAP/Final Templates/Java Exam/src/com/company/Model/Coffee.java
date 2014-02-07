package com.company.Model;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class Coffee extends Product {
    public String type;

    @Override
    public String toString() {
        return String.format("Coffee, %s, %s, %d", servingType, type, price);
    }
}
