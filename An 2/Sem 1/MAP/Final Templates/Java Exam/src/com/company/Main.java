package com.company;

import com.company.Controller.Controller;
import com.company.Model.Product;
import com.company.Repository.Repository;
import com.company.View.MainWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Repository<Product> repo = new Repository<Product>();
        final Controller controller = new Controller(repo);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow(controller);
                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
