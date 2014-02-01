package lab8;

import lab8.Controller.Controller;
import lab8.Model.Student;
import lab8.Repository.Repository;
import lab8.UI.GUI;
import lab8.UI.UI;
import lab8.Utils.StackException;

import javax.swing.*;
import java.io.IOException;
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
    public static void main(String[] args) throws IOException, StackException {
        Repository<Student> repo = new Repository<Student>();
        final Controller controller = new Controller(repo);
//        UI ui = new UI(controller);


        controller.addStudent(0,"asfas",6);
        controller.addStudent(1,"galkdj",10);
        controller.addStudent(2,"fkleaj",9,10);
        controller.addStudent(3,"fekljaj",7,10,"alsjkf","alkfja");

//        Stack<Student> source = repo.allElements();
//        Stack<Student> dest = new Stack<Student>();
//
//        Student topStudent = repo.getTopElement();
//
//        Controller.moveElements(source, dest);
//        ArrayList<String> strings = Controller.elementsFromStack(dest);
//
//        for (String student : strings) {
//            System.out.println(student);
//        }
//
//        System.out.println(controller.numberOfStudentGreaterThan(topStudent));
//        repo.serializeDataToFile("whateverman.txt");
//        repo.deserializeDataFromFile("whateverman.txt");

        Map<Integer, Student> source = repo.allElements();
        Map<Integer, Student> dest = new LinkedHashMap<Integer, Student>();

        Controller.moveElements(source, dest);

        for (Student student : dest.values()) {
            System.out.println(student.toString());
        }

        repo.saveRepoToFile("textfile.txt");
        controller.readRepoFromFile("textfile.txt");

//        ui.showMenu();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui = new GUI(controller);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
