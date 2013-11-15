package lab2.Controller;

import lab2.Model.GraduateStudent;
import lab2.Model.PhDStudent;
import lab2.Model.Student;
import lab2.Model.UndergraduateStudent;
import lab2.Repository.Repository;
import lab2.Repository.Stack;
import lab2.Utils.StackException;

import java.util.ArrayList;

/**
 *
 * @author mihai
 */
public class Controller {
    private Repository repo;

    /**
     *
     * Gets a String representation of all the students from a given Stack
     * @param stack The stack to process
     * @return An ArrayList<String> of all the String representations of the elements from the given Stack
     */
    public static ArrayList<String> studentsFromStack(Stack<Student> stack) {
        Stack<Student> copy = stack.copy();
        ArrayList<String> students = new ArrayList<String>();

        while (!copy.isEmpty()) {
            try {
                Student student = copy.pop();
                String studentString;
                studentString = String.format("%d|%s|%d|", student.id, student.name, student.grade);
                if (student.getClass() == GraduateStudent.class) {
                    String concatString;
                    GraduateStudent gradStud = (GraduateStudent)student;
                    concatString = String.format("%d|%d|%s|", gradStud.grade2, gradStud.grade3, gradStud.supervisor);
                    studentString = studentString.concat(concatString);
                } else if (student.getClass() == UndergraduateStudent.class) {
                    String concatString;
                    UndergraduateStudent gradStud = (UndergraduateStudent)student;
                    concatString = String.format("%d|", gradStud.grade2);
                    studentString = studentString.concat(concatString);
                } else if (student.getClass() == PhDStudent.class) {
                    String concatString;
                    PhDStudent phdStudent = (PhDStudent)student;
                    concatString = String.format("%d|%s|%s|", phdStudent.grade2, phdStudent.supervisor, phdStudent.thesis);
                    studentString = studentString.concat(concatString);
                }
                students.add(studentString);
            } catch (StackException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    /**
     *
     * Moves all elements from a stack to another stack
     * @param source The stack to move the elements from
     * @param destination The stack to move the elements to
     */
    public static void moveElements(Stack source, Stack destination) {
        Stack temp = new Stack();
        while (!source.isEmpty()) {
            try {
                temp.push(source.pop());
            } catch (StackException e) {
                System.out.println(e.getMessage());
            }
        }
        while (!temp.isEmpty()) {
            try {
                destination.push(temp.pop());
            } catch (StackException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * 
     * Constructor.
     * 
     * @param repo The repository to be used. Cannot be null.
     */
    public Controller(Repository repo){
        this.repo = repo;
    }
    
    /**
     * 
     * Controller method to add a new student to the repository.
     * 
     * @param id Student id. Must be a unique positive int.
     * @param name Student name. Cannot be an empty string.
     * @param grade Student grade. Must be an int between 1 and 10.
     * @return A list of error messages from the Validator.
     */
    public ArrayList<String> addStudent(int id, String name, int grade){
        Student student = new Student(id, name, grade);
        Validator validator = new Validator(this.repo);
        ArrayList<String> errorList = validator.validateStudent(student);
        
        if (errorList.isEmpty()){
            repo.addStudent(student);
        }
        return errorList;
    }

    public ArrayList<String> addStudent(int id, String name, int grade, int grade2){
        Student student = new UndergraduateStudent(id, name, grade, grade2);
        Validator validator = new Validator(this.repo);
        ArrayList<String> errorList = validator.validateStudent(student);

        if (errorList.isEmpty()){
            repo.addStudent(student);
        }
        return errorList;
    }

    public ArrayList<String> addStudent(int id, String name, int grade, int grade2, int grade3, String supervisor){
        Student student = new GraduateStudent(id, name, grade, supervisor, grade2, grade3);
        Validator validator = new Validator(this.repo);
        ArrayList<String> errorList = validator.validateStudent(student);

        if (errorList.isEmpty()){
            repo.addStudent(student);
        }
        return errorList;
    }

    public ArrayList<String> addStudent(int id, String name, int grade, int grade2, String supervisor, String thesis){
        Student student = new PhDStudent(id, name, grade, supervisor, thesis, grade2);
        Validator validator = new Validator(this.repo);
        ArrayList<String> errorList = validator.validateStudent(student);

        if (errorList.isEmpty()){
            repo.addStudent(student);
        }
        return errorList;
    }
    
    /**
     * 
     * Removes students from the repository until a student with grade == 0 is found.
     */
    public void removeStudentsUntilMaxGrade() throws StackException{
        Student currentStudent = this.repo.getTopStudent();
        while (currentStudent.average() != 10){
            this.repo.removeStudent(currentStudent);
            currentStudent = this.repo.getTopStudent();
        }
    }
    
    /**
     * 
     * Retrieves the entire student list from the repository.
     * 
     * @return A stack of students from the repository.
     */
    public ArrayList<String> allStudents() {
        return Controller.studentsFromStack(this.repo.allStudents());
    }
    
    /**
     * 
     * Gets the number of students from the repository.
     * 
     * @return Total number of students in the repository.
     */
    public int numberOfStudents() {
        return this.repo.numberOfStudents();
    }

    /**
     *
     * Computes the total number of Students greater than a given student
     * @param id The id of the student to compare to.
     * @return The number of students greater than the given student.
     */
    public int numberOfStudentGreaterThan(int id) {
        Stack<Student> allStudents = this.repo.allStudents();
        Student student = null;
        int no = -1;
        while (!allStudents.isEmpty()) {
            try {
                student = allStudents.pop();
                if (student.id == id) {
                    break;
                }
            } catch (StackException e) {
                e.printStackTrace();
            }
        }
        if (student != null) {
            no = this.repo.numberOfStudentsGreaterThan(student);
        }
        return no;
    }
}
