package com.mcostea.SalesAgency.view;

import com.mcostea.SalesAgency.controller.Controller;
import com.mcostea.SalesAgency.model.Agent;
import com.mcostea.SalesAgency.model.Client;
import com.mcostea.SalesAgency.model.Order;
import com.mcostea.SalesAgency.model.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mihaicostea on 16/04/15.
 */
public class ClientForm implements Observer {
    private Controller controller;

    private ArrayList<Order> orders;

    public ClientForm(Controller controller) {
        this.controller = controller;

        JFrame frame = new JFrame("ClientForm");

        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        this.controller.addObserver(this);
        this.controller.getOrders();

        this.addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product();
                product.setName(productNameTextField1.getText());
                product.setPrice(Integer.parseInt(productPriceTextField1.getText()));
                product.setQuantity(Integer.parseInt(productQuantityTextField1.getText()));

                controller.sendProduct(product);
            }
        });

        this.addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order o = new Order();
                Product p = new Product();
                p.setProductId(Integer.parseInt(productIdTextField.getText()));
                o.setProduct(p);
                Client c = new Client();
                c.setClientID(Integer.parseInt(clientIdTextField.getText()));
                o.setClient(c);
                Agent a = new Agent();
                a.setAgentID(Integer.parseInt(agentIdTextField.getText()));
                o.setAgent(a);

                o.setQuantity(Integer.parseInt(quantityTextField1.getText()));
                o.setStatus(statusTextField1.getText());

                controller.sendOrder(o);
            }
        });
    }

    private JTable table1;
    private JPanel panel1;
    private JTextField quantityTextField;
    private JTextField statusTextField;
    private JTextField productNameTextField;
    private JTextField productPriceTextField;
    private JTextField productQuantityTextField;
    private JTextField clientNameTextField;
    private JTextField clientAddressTextField;
    private JTextField agentNameTextField;
    private JTextField productNameTextField1;
    private JTextField productPriceTextField1;
    private JTextField productQuantityTextField1;
    private JButton addProductButton;
    private JTextField clientNameTextField1;
    private JTextField clientAddressTextField1;
    private JButton addClientButton;
    private JTextField productIdTextField;
    private JTextField agentIdTextField;
    private JTextField clientIdTextField;
    private JTextField quantityTextField1;
    private JTextField statusTextField1;
    private JButton addOrderButton;

    private void createUIComponents() {
        this.table1 = new JTable(new DefaultTableModel(new Object[]{"OrderID", "Client Name", "Agent Name", "Product Name", "Quantity", "Status"}, 0));
        this.table1.setShowGrid(true);
        this.table1.setAutoCreateColumnsFromModel(true);
        this.table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Order currentOrder = orders.get(table1.getSelectedRow());
                quantityTextField.setText(String.valueOf(currentOrder.getQuantity()));
                statusTextField.setText(currentOrder.getStatus());
                productNameTextField.setText(currentOrder.getProduct().getName());
                productPriceTextField.setText(String.valueOf(currentOrder.getProduct().getPrice()));
                productQuantityTextField.setText(String.valueOf(currentOrder.getProduct().getQuantity()));
                clientNameTextField.setText(currentOrder.getClient().getName());
                clientAddressTextField.setText(currentOrder.getClient().getAddress());
                agentNameTextField.setText(currentOrder.getAgent().getName());
            }
        });
    }

    private void addTableComponents(ArrayList<Order> orders) {
        this.table1.removeAll();

        this.orders = orders;

        DefaultTableModel model = (DefaultTableModel) this.table1.getModel();
        model.setRowCount(0);

        for (Order o : orders) {
            model.addRow(new Object[]{o.getOrderId(), o.getClient().getName(), o.getAgent().getName(), o.getProduct().getName(), o.getQuantity(), o.getStatus()});
        }
    }

    public void update(Observable o, Object arg) {
        this.addTableComponents((ArrayList<Order>) arg);
    }
}
