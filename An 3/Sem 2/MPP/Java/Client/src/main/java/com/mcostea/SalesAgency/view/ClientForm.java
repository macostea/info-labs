package com.mcostea.SalesAgency.view;

import com.mcostea.SalesAgency.controller.Controller;
import com.mcostea.SalesAgency.model.Client;

import javax.swing.*;

/**
 * Created by mihaicostea on 16/04/15.
 */
public class ClientForm {
    private Controller controller;

    public ClientForm(Controller controller) {
        this.controller = controller;

        JFrame frame = new JFrame("ClientForm");

        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JTable table1;
    private JPanel panel1;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
