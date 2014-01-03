package lab8.UI;

import lab8.Controller.Controller;
import lab8.Utils.StackException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author mihai
 */
public class UI {
    private Controller controller;
    private StudentsObserver lessThanFiveObserver = new StudentsObserver("<");
    private StudentsObserver greaterOrEqualToFiveObserver = new StudentsObserver(">=");

    final String kGreeting = "Welcome!";
    final String kExitString = "x";
    final String kMenuDelimiter = "----------\n";
    final String kMenuExit = "(x) Exit";
    final String kMenu = "(1) Add student\n"
            + "(2) Remove students until 10-grade student is found\n"
            + "(3) All students\n"
            + "(4) Number of students\n"
            + "(5) Save students to file\n"
            + kMenuDelimiter
            + kMenuExit;
    final String kStudentAddedMessage = "Student Added!";
    final String kStudentsRemovedMessage = "Students Removed!";
    
    public UI(Controller controller) {
        this.controller = controller;
        this.controller.addObserver(this.lessThanFiveObserver);
        this.controller.addObserver(this.greaterOrEqualToFiveObserver);
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
                } else if ("5".equals(userInput)) {
                    this.saveStudentsToFile();
                }
            } catch (InputMismatchException e) {
                
            }
   
        }
    }
    
    private void addStudent() {
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        int id, grade, grade2, grade3, studentType;
        String name, supervisor, thesis;
        ArrayList <String> errorList;
                
        try{
            System.out.print("Student ID: ");
            id = input.nextInt();
            input.nextLine();
            System.out.print("Student Name: ");
            name = input.nextLine();
            System.out.print("Student Grade: ");
            grade = input.nextInt();

            System.out.print("Student Type:\n(1)Regular\n(2)Graduate\n(3)Undergraduate\n(4)PhD");
            studentType = input.nextInt();

            switch (studentType) {
                case 1:
                    errorList = this.controller.addStudent(id, name, grade);
                    break;
                case 2:
                    System.out.print("Grade2: ");
                    grade2 = input.nextInt();
                    input.nextLine();
                    System.out.print("Grade3: ");
                    grade3 = input.nextInt();
                    input.nextLine();
                    System.out.print("Supervisor: ");
                    supervisor = input.nextLine();

                    errorList = this.controller.addStudent(id, name, grade, grade2, grade3, supervisor);
                    break;
                case 3:
                    System.out.print("Grade2: ");
                    grade2 = input.nextInt();

                    errorList = this.controller.addStudent(id, name, grade, grade2);
                    break;
                case 4:
                    System.out.print("Grade2: ");
                    grade2 = input.nextInt();
                    input.nextLine();
                    System.out.print("Supervisor: ");
                    supervisor = input.nextLine();
                    System.out.print("Thesis: ");
                    thesis = input.nextLine();

                    errorList = this.controller.addStudent(id, name, grade, grade2, supervisor, thesis);
                    break;
                default:
                    System.out.print("Invalid choice!");
                    return;
            }
            if (!errorList.isEmpty()) {
                for (String error : errorList) {
                    System.out.println(error);
                }
            } else {
                System.out.println(this.kStudentAddedMessage);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
        
    }
    
    private void removeStudents() {
        try {
            this.controller.removeStudentsUntilMaxGrade();
            System.out.println(this.kStudentsRemovedMessage);
        } catch (StackException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void allStudents() {
        ArrayList<String> allStudents = this.controller.allStudents();
        for (String student : allStudents) {
            System.out.println(student);
        }
    }
    
    private void numberOfStudents() {
        System.out.printf("Total number of students: %d\n", this.controller.numberOfStudents());
    }

    private void saveStudentsToFile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Filename: ");
        String filename = input.nextLine();

        this.controller.saveStudentsToFile(filename);
    }
}
