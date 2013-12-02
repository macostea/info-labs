package lab8.Controller;

import lab8.Model.Comparable;
import lab8.Model.*;
import lab8.Model.Readable;
import lab8.Repository.Repository;
import lab8.Utils.StackException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author mihai
 */
public class Controller {
    private Repository<Student> repo;

    /**
     *
     * Gets a String representation of all the elements from a given Map
     * @param map The Map to process
     * @return An ArrayList<String> of all the String representations of the elements from the given Stack
     */
    public static<T> ArrayList<String> elementsFromMap(Map<Integer, T> map) {
        ArrayList<String> strings = new ArrayList<String>();
        for (T element : map.values()) {
            strings.add(element.toString());
        }
        return strings;
    }

    /**
     *
     * Moves all elements from a map to another map
     * @param source The map to move the elements from
     * @param destination The map to move the elements to
     */
    public static<T> void moveElements(Map<Integer, ? extends HasId> source, Map<Integer,T> destination) {
        for (HasId element : source.values()){
            T elementT = (T)element;
            destination.put(element.getId(), elementT);
        }
    }

//    /**
//     *
//     * Moves all elements from a stack to another stack
//     * @param source The stack to move the elements from
//     * @param destination The stack to move the elements to
//     */
//    public static void moveElements2(Stack<? extends Student> source, Stack<? super Student> destination) {
//        Stack<Student> temp = new Stack<Student>();
//        while (!source.isEmpty()) {
//            try {
//                temp.push(source.pop());
//            } catch (StackException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        while (!temp.isEmpty()) {
//            try {
//                destination.push(temp.pop());
//            } catch (StackException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
    
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
        while (currentStudent != null && currentStudent.average() != 10){
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
        return Controller.elementsFromMap(this.repo.allElements());
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
        Map<Integer, Student> allStudents = this.repo.allElements();
        int no = 0;

        for (Comparable<Student> comparableStudent : allStudents.values()) {
            if (comparableStudent.isGreaterThan(student)) {
                no++;
            }
        }
        return no;
    }


    /**
     *
     * Saves the assigned repository to a text file
     *
     * @param filename The name of the file to save in.
     */
    public void saveStudentsToFile(String filename) {
        this.repo.saveRepoToFile(filename);
    }

    /**
     *
     * Reads the repository from a text file
     *
     * @param filename The filename of the file.
     */
    public void readRepoFromFile(String filename) {
        BufferedReader reader;
        Map<Integer, Student> map = new LinkedHashMap<Integer, Student>();

        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filename),
                    "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("[|]+");
                Readable element;
                if (tokens[0].contains("GraduateStudent")) {
                    element = new GraduateStudent();
                } else if (tokens[0].contains("PhDStudent")) {
                    element = new PhDStudent();
                } else if (tokens[0].contains("UndergraduateStudent")) {
                    element = new UndergraduateStudent();
                } else if (tokens[0].contains("Student")) {
                    element = new Student();
                } else {
                    return;
                }

                element.readAttributesFromString(line);
                Student student = (Student) element;
                map.put(student.getId(), student);
            }
            this.repo = new Repository<Student>(map);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
