package com.mcostea.SalesAgency.protocol;

import com.mcostea.SalesAgency.model.Order;

/**
 * Created by mihaicostea on 05/05/15.
 */
public class UpdateOrderPacket extends Packet {
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
