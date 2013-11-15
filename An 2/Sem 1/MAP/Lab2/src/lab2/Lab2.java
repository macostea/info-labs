package lab2;

import java.io.IOException;

/**
 *
 * @author mihai
 */
public class Lab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Repository repo = new Repository();
        Controller controller = new Controller(repo);
        UI ui = new UI(controller);
        
        ui.showMenu();
    }
}
