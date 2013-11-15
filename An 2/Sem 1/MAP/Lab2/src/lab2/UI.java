package lab2;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author mihai
 */
public class UI {
    private Controller controller;

    private final String kGreeting = "Welcome!";
    private final String kExitString = "x";
    private final String kMenuDelimiter = "----------\n";
    private final String kMenuExit = "(x) Exit";
    private final String kMenu = "(1) Add student\n"
            + "(2) Remove students until 10-grade student is found\n"
            + "(3) All students\n"
            + "(4) Number of students\n"
            + kMenuDelimiter
            + kMenuExit;
    private final String kStudentAddedMessage = "Student Added!";
    private final String kStudentsRemovedMessage = "Students Removed!";
    
    public UI(Controller controller) {
        this.controller = controller;
    }
    
    public void showMenu() throws IOException {
        System.out.println(this.kGreeting);
        String userInput = "";
        Scanner input = new Scanner(System.in);
        while (!this.kExitString.equals(userInput)) {
            System.out.println(this.kMenu);
            try {
                userInput = input.nextLine();
                if ("1".equals(userInput)) {
                    this.addStudent();
                } else if ("2".equals(userInput)) {
                    this.removeStudents();
                } else if ("3".equals(userInput)) {
                    this.allStudents();
                } else if ("4".equals(userInput)) {
                    this.numberOfStudents();
                }
            } catch (NoSuchElementException|IllegalStateException e) {
                
            }
   
        }
    }
    
    private void addStudent() {
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        int id, grade;
        String name;
                
        try{
            System.out.print("Student ID: ");
            id = input.nextInt();
            input.nextLine();
            System.out.print("Student Name: ");
            name = input.nextLine();
            System.out.print("Student Grade: ");
            grade = input.nextInt();

            ArrayList<String> errorList = this.controller.addStudent(id, name, grade);
            if (!errorList.isEmpty()) {
                for (String error : errorList) {
                    System.out.println(error);
                }
            } else {
                System.out.println(this.kStudentAddedMessage);
            }
        } catch (IllegalStateException|NoSuchElementException e) {
            System.out.println("Invalid input!");
        }
        
    }
    
    private void removeStudents() {
        this.controller.removeStudentsUntilMaxGrade();
        System.out.println(this.kStudentsRemovedMessage);
    }
    
    private void allStudents() {
        Stack allStudents = this.controller.allStudents();
        while (!allStudents.isEmpty()) {
            Student currStudent = allStudents.pop();
            System.out.printf("%d | %s | %d\n", currStudent.id, currStudent.name, currStudent.grade);
        }
    }
    
    private void numberOfStudents() {
        System.out.printf("Total number of students: %d\n", this.controller.numberOfStudents());
    }
}
