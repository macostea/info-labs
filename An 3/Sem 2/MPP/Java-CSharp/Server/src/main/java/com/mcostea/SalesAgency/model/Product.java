package com.mcostea.SalesAgency.model;

import java.io.Serializable;

/**
 * Created by mihaicostea on 16/04/15.
 */
public class Product implements Serializable {
    private int productId;
    private String name;
    private int price;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
