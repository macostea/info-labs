package com.mcostea.SalesAgency;

import com.mcostea.SalesAgency.controller.Controller;
import com.mcostea.SalesAgency.view.ClientForm;

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
