import server.ServerListener;

import javax.swing.*;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class Main extends JFrame {
    public static void main(String args[]) {
        ServerListener serverListener = new ServerListener();
        serverListener.start();

        Main main = new Main();
//        main.setVisible(true);
    }
}
