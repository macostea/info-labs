package com.mcostea.SalesAgency.protocol;

import java.io.Serializable;

/**
 * Created by mihaicostea on 21/04/15.
 */
public enum RequestType implements Serializable {
    GET_ALL_ORDERS,
    ADD_ORDER,
    UPDATE_ORDER,
    REMOVE_ORDER,
    UPDATE_NOTIFICATION,
    SERVER_RESPONSE,
    ERROR
}
