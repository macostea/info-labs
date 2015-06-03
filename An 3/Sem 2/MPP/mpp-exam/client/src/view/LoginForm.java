package view;

import controller.Controller;
import model.User;
import protocol.LoginPacket;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class LoginForm implements Observer {
    private Controller controller;
    private JPanel panel1;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton loginButton;

    public LoginForm(Controller controller) {
        this.controller = controller;
        this.controller.addObserver(this);

        JFrame frame = new JFrame("Login");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            User u = new User();
            u.setUserName(usernameTextField.getText());
            u.setPassword(passwordTextField.getText());

            controller.loginUser(u);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass().equals(LoginPacket.class)) {
            LoginPacket serverResponse = (LoginPacket)arg;
            if (serverResponse.isLoggedIn()) {
                System.out.println("Logged in");
                UserForm userForm = new UserForm(this.controller);
                userForm.setCurrentUser(serverResponse.getUser());
                this.panel1.setVisible(false);
                this.controller.deleteObserver(this);
            } else {
                System.out.println("login failure");
            }
        }
    }
}
