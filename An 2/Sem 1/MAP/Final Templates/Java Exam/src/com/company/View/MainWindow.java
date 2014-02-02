package com.company.View;

import com.company.Controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class MainWindow extends JFrame {
    private Controller controller;

    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    public MainWindow(Controller controller) {
        this.controller = controller;

        this.setPreferredSize(new Dimension(600,600));

        this.leftPanel.setLayout(new BoxLayout(this.leftPanel, BoxLayout.Y_AXIS));
        this.leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 350));
        this.leftPanel.setOpaque(false);

        this.rightPanel.setLayout(new BoxLayout(this.rightPanel, BoxLayout.Y_AXIS));
        this.rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 390, 20, 20));
        this.rightPanel.setOpaque(false);

        //this.addThings()

        this.getContentPane().add(this.rightPanel, BorderLayout.LINE_END);
        this.getContentPane().add(this.leftPanel, BorderLayout.LINE_START);

        this.pack();
        this.setVisible(true);
    }
}
