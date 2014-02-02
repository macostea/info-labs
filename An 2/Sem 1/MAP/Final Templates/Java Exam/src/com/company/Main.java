package com.company;

import com.company.Controller.Controller;
import com.company.View.MainWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        final Controller controller = new Controller();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow(controller);
                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
