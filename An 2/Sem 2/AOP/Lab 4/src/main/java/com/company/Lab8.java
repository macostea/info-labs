package com.company;


import java.io.IOException;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.Controller.Controller;
import com.company.Model.Student;
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
    	ApplicationContext factory = new ClassPathXmlApplicationContext("spring-students.xml");
    	
        final Controller controller = (Controller)factory.getBean("controller");

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI gui = new GUI(controller);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
