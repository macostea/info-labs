package com.company.View;

import com.company.Controller.Controller;
import com.company.Model.Cake;
import com.company.Model.Coffee;
import com.company.Model.Product;
import com.company.Model.Sandwich;
import com.sun.org.apache.bcel.internal.generic.FLOAD;
import com.sun.tools.javac.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class MainWindow extends JFrame {
    private Controller controller;

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JTextField typeBox = new JTextField();
    private JTextField priceBox = new JTextField();
    private JTextField weightBox = new JTextField();
    private JTextField contentBox = new JTextField();
    private JTextField shapeBox = new JTextField();

    private JComboBox productType = new JComboBox();
    private JComboBox servingType = new JComboBox();

    private JTable table = new JTable();
    private DefaultTableModel model = new DefaultTableModel();

    private JComboBox filterServingType = new JComboBox();
    private JTextField filterPriceBox = new JTextField();

    public MainWindow(Controller controller) {
        this.controller = controller;

        this.setPreferredSize(new Dimension(600,600));

        this.leftPanel.setLayout(new BoxLayout(this.leftPanel, BoxLayout.Y_AXIS));
        this.leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 350));
        this.leftPanel.setOpaque(false);

        this.rightPanel.setLayout(new BoxLayout(this.rightPanel, BoxLayout.Y_AXIS));
        this.rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 390, 20, 20));
        this.rightPanel.setOpaque(false);

        this.addTextBoxes();
        this.addProductTypeComboBox();
        this.addServingType();
        this.addNewProductButton();
        this.addSaveToFileButton();

        this.addTable();
        this.addFilterSection();

        this.getContentPane().add(this.rightPanel, BorderLayout.LINE_END);
        this.getContentPane().add(this.leftPanel, BorderLayout.LINE_START);

        this.pack();
        this.setVisible(true);
    }

    public void addTextBoxes() {
        JLabel typeLabel = new JLabel();
        typeLabel.setText("Type");
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        typePanel.add(typeLabel);
        this.typeBox.setPreferredSize(new Dimension(100,20));
        typePanel.add(this.typeBox);

        JLabel priceLabel = new JLabel();
        priceLabel.setText("Price");
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        pricePanel.add(priceLabel);
        this.priceBox.setPreferredSize(new Dimension(100,20));
        pricePanel.add(this.priceBox);

        JLabel weightLabel = new JLabel();
        weightLabel.setText("Weight");
        JPanel weightPanel = new JPanel();
        weightPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        weightPanel.add(weightLabel);
        this.weightBox.setPreferredSize(new Dimension(100,20));
        weightPanel.add(this.weightBox);

        JLabel contentLabel = new JLabel();
        contentLabel.setText("Content");
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        contentPanel.add(contentLabel);
        this.contentBox.setPreferredSize(new Dimension(100,20));
        contentPanel.add(this.contentBox);

        JLabel shapeLabel = new JLabel();
        shapeLabel.setText("Shape");
        JPanel shapePanel = new JPanel();
        shapePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        shapePanel.add(shapeLabel);
        this.shapeBox.setPreferredSize(new Dimension(100,20));
        shapePanel.add(this.shapeBox);

        this.leftPanel.add(typePanel);
        this.leftPanel.add(pricePanel);
        this.leftPanel.add(weightPanel);
        this.leftPanel.add(contentPanel);
        this.leftPanel.add(shapePanel);
    }

    private void addProductTypeComboBox() {
        this.productType.setPreferredSize(new Dimension(100,20));
        this.productType.addItem("Coffee");
        this.productType.addItem("Sandwich");
        this.productType.addItem("Cake");

        this.productType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (productType.getSelectedItem().equals("Coffee")) {
                    typeBox.setEnabled(true);
                    servingType.setEnabled(true);
                    priceBox.setEnabled(true);
                    weightBox.setEnabled(false);
                    contentBox.setEnabled(false);
                    shapeBox.setEnabled(false);
                } else if (productType.getSelectedItem().equals("Sandwich")) {
                    typeBox.setEnabled(false);
                    servingType.setEnabled(true);
                    priceBox.setEnabled(true);
                    weightBox.setEnabled(true);
                    contentBox.setEnabled(true);
                    shapeBox.setEnabled(false);
                } else if (productType.getSelectedItem().equals("Cake")) {
                    typeBox.setEnabled(true);
                    servingType.setEnabled(false);
                    servingType.setSelectedItem("Cold");
                    priceBox.setEnabled(true);
                    weightBox.setEnabled(false);
                    contentBox.setEnabled(false);
                    shapeBox.setEnabled(true);
                }
            }
        });

        this.leftPanel.add(this.productType);
    }

    private void addServingType() {
        this.servingType.setPreferredSize(new Dimension(100,20));
        this.servingType.addItem("Hot");
        this.servingType.addItem("Cold");

        this.leftPanel.add(this.servingType);
    }

    private void addNewProductButton() {
        JButton addProductBtn = new JButton();
        addProductBtn.setPreferredSize(new Dimension(100, 40));
        addProductBtn.setText("Add Product");

        addProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (productType.getSelectedItem().equals("Coffee")) {
                    controller.addCoffee(typeBox.getText(), servingType.getSelectedItem().toString(), Integer.parseInt(priceBox.getText()));
                } else if (productType.getSelectedItem().equals("Sandwich")) {
                    controller.addSandwich(Integer.parseInt(weightBox.getText()), contentBox.getText(), servingType.getSelectedItem().toString(), Integer.parseInt(priceBox.getText()));
                } else if (productType.getSelectedItem().equals("Cake")) {
                    controller.addCake(shapeBox.getText(), typeBox.getText(), Integer.parseInt(priceBox.getText()));
                }
            }
        });

        this.leftPanel.add(addProductBtn);
    }

    private void addSaveToFileButton() {
        JButton saveToFileBtn = new JButton();
        saveToFileBtn.setPreferredSize(new Dimension(100, 40));
        saveToFileBtn.setText("Save to file");

        saveToFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.writeToFile("Coffee Shop.txt");
            }
        });

        this.leftPanel.add(saveToFileBtn);
    }

    private void addTable() {
        this.table.setPreferredSize(new Dimension(350, 400));
        this.table.setVisible(true);

        this.fillTable(null);

        this.rightPanel.add(this.table);
    }

    private void fillTable(List<Product> list) {
        if (list == null){
            list = this.controller.allProducts();
        }
        this.model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Vector vector = new Vector();
        for (Product product : list) {
            Vector row = new Vector();
            if (product.getClass().equals(Cake.class)) {
                Cake cake = (Cake)product;
                row.add(cake.price);
                row.add(cake.servingType);
                row.add(cake.type);
                row.add("");
                row.add("");
                row.add(cake.shape);
            } else if (product.getClass().equals(Coffee.class)) {
                Coffee coffee = (Coffee)product;
                row.add(coffee.price);
                row.add(coffee.servingType);
                row.add(coffee.type);
                row.add("");
                row.add("");
                row.add("");
            } else if (product.getClass().equals(Sandwich.class)) {
                Sandwich sandwich = (Sandwich)product;
                row.add(sandwich.price);
                row.add(sandwich.servingType);
                row.add("");
                row.add(sandwich.weight);
                row.add(sandwich.content);
                row.add("");
            }
            vector.add(row);
        }

        Vector columnNames = new Vector();
        columnNames.add("Price");
        columnNames.add("Serving Type");
        columnNames.add("Type");
        columnNames.add("Weight");
        columnNames.add("Content");
        columnNames.add("Shape");

        this.model.setDataVector(vector, columnNames);

        this.table.setModel(this.model);
    }

    private void addFilterSection() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        this.filterServingType.addItem("Hot");
        this.filterServingType.addItem("Cold");
        this.filterServingType.setPreferredSize(new Dimension(100, 20));
        this.filterPriceBox.setPreferredSize(new Dimension(100, 20));

        JButton reloadBtn = new JButton();
        reloadBtn.setPreferredSize(new Dimension(100, 40));
        reloadBtn.setText("Reload");

        reloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fillTable(controller.filteredProducts(filterServingType.getSelectedItem().toString(), Integer.parseInt(filterPriceBox.getText())));
            }
        });

        filterPanel.add(this.filterServingType);
        filterPanel.add(this.filterPriceBox);
        filterPanel.add(reloadBtn);

        this.rightPanel.add(filterPanel);
    }
}
