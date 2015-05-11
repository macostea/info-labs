package com.mcostea.SalesAgency.protocol;

import com.mcostea.SalesAgency.model.Order;

import java.io.Serializable;
import java.util.ArrayList;

public class Packet implements Serializable {
    private String packetType;
    private ArrayList<Order> orders;
    private String message;
    private Order orderToProcess;

    public Packet() {
    }

    public String getPacketType() {
        return packetType;
    }

    public void setPacketType(String packetType) {
        this.packetType = packetType;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrderToProcess() {
        return orderToProcess;
    }

    public void setOrderToProcess(Order orderToProcess) {
        this.orderToProcess = orderToProcess;
    }

}
