package lab2.Controller;

import lab2.Model.*;
import lab2.Model.Comparable;
import lab2.Repository.Repository;
import lab2.Repository.Stack;
import lab2.Utils.StackException;

import java.util.ArrayList;

/**
 *
 * @author mihai
 */
public class Controller {
    private Repository<Student> repo;

    /**
     *
     * Gets a String representation of all the elements from a given Stack
     * @param stack The stack to process
     * @return An ArrayList<String> of all the String representations of the elements from the given Stack
     */
    public static<T> ArrayList<String> elementsFromStack(Stack<T> stack) {
        Stack<T> copy = stack.copy();
        ArrayList<String> strings = new ArrayList<String>();

        while (!copy.isEmpty()) {
            try {
                T element = copy.pop();
                strings.add(element.toString());
            } catch (StackException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }

    /**
     *
     * Moves all elements from a stack to another stack
     * @param source The stack to move the elements from
     * @param destination The stack to move the elements to
     */
    public static<T> void moveElements(Stack<? extends T> source, Stack<T> destination) {
        Stack<T> temp = new Stack<T>();
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
    public Controller(Repository<Student> repo){
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
            repo.addElement(student);
        }
        return errorList;
    }

    public ArrayList<String> addStudent(int id, String name, int grade, int grade2){
        Student student = new UndergraduateStudent(id, name, grade, grade2);
        Validator validator = new Validator(this.repo);
        ArrayList<String> errorList = validator.validateStudent(student);

        if (errorList.isEmpty()){
            repo.addElement(student);
        }
        return errorList;
    }

    public ArrayList<String> addStudent(int id, String name, int grade, int grade2, int grade3, String supervisor){
        Student student = new GraduateStudent(id, name, grade, supervisor, grade2, grade3);
        Validator validator = new Validator(this.repo);
        ArrayList<String> errorList = validator.validateStudent(student);

        if (errorList.isEmpty()){
            repo.addElement(student);
        }
        return errorList;
    }

    public ArrayList<String> addStudent(int id, String name, int grade, int grade2, String supervisor, String thesis){
        Student student = new PhDStudent(id, name, grade, supervisor, thesis, grade2);
        Validator validator = new Validator(this.repo);
        ArrayList<String> errorList = validator.validateStudent(student);

        if (errorList.isEmpty()){
            repo.addElement(student);
        }
        return errorList;
    }
    
    /**
     * 
     * Removes students from the repository until a student with grade == 0 is found.
     */
    public void removeStudentsUntilMaxGrade() throws StackException{
        Student currentStudent = this.repo.getTopElement();
        while (currentStudent.average() != 10){
            this.repo.removeElement(currentStudent);
            currentStudent = this.repo.getTopElement();
        }
    }
    
    /**
     * 
     * Retrieves the entire student list from the repository.
     * 
     * @return A stack of students from the repository.
     */
    public ArrayList<String> allStudents() {
        return Controller.elementsFromStack(this.repo.allElements());
    }
    
    /**
     * 
     * Gets the number of students from the repository.
     * 
     * @return Total number of students in the repository.
     */
    public int numberOfStudents() {
        return this.repo.numberOfElements();
    }

    /**
     *
     * Computes the total number of Students greater than a given student
     * @param student The student to compare to.
     * @return The number of students greater than the given student.
     */
    public int numberOfStudentGreaterThan(Student student) {
        Stack<Student> allStudents = this.repo.allElements();
        int no = 0;

        while (!allStudents.isEmpty()) {
            try {
                Comparable<Student> comparableStudent = allStudents.pop();
                if (comparableStudent.isGreaterThan(student)) {
                    no++;
                }
            } catch (StackException e) {
                System.out.println(e.getMessage());
            }
        }

        return no;
    }
}
