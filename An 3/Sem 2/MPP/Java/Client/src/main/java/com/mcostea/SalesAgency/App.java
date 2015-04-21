package com.mcostea.SalesAgency;

import com.mcostea.SalesAgency.controller.Controller;
import com.mcostea.SalesAgency.model.Client;
import com.mcostea.SalesAgency.model.Order;
import com.mcostea.SalesAgency.protocol.Packet;
import com.mcostea.SalesAgency.protocol.RequestType;
import com.mcostea.SalesAgency.view.ClientForm;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{

    private static Controller controller;
    private static ClientForm clientForm;
    public static void main(final String args[]) {
        App.controller = new Controller();

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            App.clientForm = new ClientForm(App.controller);
        }
    });

    }
}
