package com.company;


import java.io.IOException;

import javax.swing.JFrame;

import com.company.Controller.Controller;
import com.company.Model.Student;
import com.company.Repository.DBRepository;
import com.company.Repository.Repository;
import com.company.Service.StoreService;
import com.company.UI.GUI;

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
        StoreService storeService = new StoreService(repo);
        final Controller controller = new Controller(storeService);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui = new GUI(controller);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
