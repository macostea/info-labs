package lab8;

import lab8.Controller.Controller;
import lab8.Model.Student;
import lab8.Repository.Repository;
import lab8.UI.UI;
import lab8.Utils.StackException;

import java.io.IOException;

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
        Controller controller = new Controller(repo);
        UI ui = new UI(controller);

        controller.addStudent(0,"asfas",10);
        controller.addStudent(1,"galkdj",9);
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

        repo.saveRepoToFile("textfile.txt");
        controller.readRepoFromFile("textfile.txt");

        ui.showMenu();
    }
}
