package com.mcostea.SalesAgency.view;

import com.mcostea.SalesAgency.servermpp.ServerListener;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerView extends javax.swing.JFrame {
    public void updateList() {

    }

    public ServerView() {
        initComponents();


        this.updateList();
    }

    private void initComponents() {


    }

    public static void main(String args[]) {
        ServerView view;
        view = new ServerView();
        view.setVisible(true);



        ServerListener serverThread = new ServerListener();
        serverThread.parent = view;
        serverThread.start();
    }
}
