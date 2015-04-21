package com.mcostea.SalesAgency.protocol;

import com.mcostea.SalesAgency.model.Order;

import java.io.Serializable;
import java.util.ArrayList;

public class Packet implements Serializable {

    RequestType requestType;
    ArrayList<Order> orders;
    Order orderToProcess;

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Order getOrderToProcess() {
        return orderToProcess;
    }

    public void setOrderToProcess(Order orderToProcess) {
        this.orderToProcess = orderToProcess;
    }
}
