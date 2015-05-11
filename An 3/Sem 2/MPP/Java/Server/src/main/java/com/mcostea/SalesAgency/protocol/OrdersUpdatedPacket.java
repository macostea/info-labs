package com.mcostea.SalesAgency.protocol;

import com.mcostea.SalesAgency.model.Order;

import java.util.ArrayList;

/**
 * Created by mihaicostea on 05/05/15.
 */
public class OrdersUpdatedPacket extends Packet {
    public ArrayList<Order> orders;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
