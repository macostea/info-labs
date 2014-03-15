package com.company;


import com.company.Controller.Controller;
import com.company.Model.Student;
import com.company.Repository.DBConnection;
import com.company.Repository.DBRepository;
import com.company.Repository.Repository;
import com.company.UI.GUI;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author mihai
 */
public class Lab8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Repository<Student> repo = new DBRepository("Students");
        final Controller controller = new Controller(repo);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui = new GUI(controller);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
