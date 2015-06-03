import controller.Controller;
import view.LoginForm;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class Main {
    private static Controller controller;
    private static LoginForm loginForm;

    public static void main(String args[]) {
        Main.controller = new Controller();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main.loginForm = new LoginForm(Main.controller);
            }
        });
    }
}
