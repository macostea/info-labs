package com.company.Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.company.Model.Comparable;
import com.company.Model.GraduateStudent;
import com.company.Model.HasId;
import com.company.Model.PhDStudent;
import com.company.Model.Readable;
import com.company.Model.Student;
import com.company.Model.UndergraduateStudent;
import com.company.Repository.Repository;
import com.company.Service.StoreService;

/**
 *
 * @author mihai
 */
public class Controller{
    private StoreService storeService;
    private static Logger logger = Logger.getLogger("Students");
    
    public StoreService getStoreService() {
    	return this.storeService;
    }
    
    private void setStoreService(StoreService storeService) {
    	this.storeService = storeService;
    }

    /**
     *
     * Gets a String representation of all the elements from a given Map
     * @param map The Map to process
     * @return An ArrayList<String> of all the String representations of the elements from the given Stack
     */
    public static<T> ArrayList<String> elementsFromMap(Map<Integer, T> map) {
        logger.info("[Entering:]Controller.elementsFromMap");
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
        logger.info("[Entering:]Controller.moveElements");
        for (HasId element : source.values()){
            T elementT = (T)element;
            destination.put(element.getId(), elementT);
        }
    }
    
    /**
     * 
     * Constructor.
     * 
     * @param repo The repository to be used. Cannot be null.
     */
    public Controller(StoreService storeService){
        logger.info("[Entering:]Controller");
        this.setStoreService(storeService);
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
        logger.info("[Entering:]Controller.addStudent(int, String, int)");
        Student student = new Student(id, name, grade);
        Validator validator = new Validator(this.storeService);
        ArrayList<String> errorList = validator.validateStudent(student);
        
        if (errorList.isEmpty()){
        	storeService.addElement(student);
        }
        return errorList;
    }

    public ArrayList<String> addStudent(int id, String name, int grade, int grade2){
        logger.info("[Entering:]Controller.addStudent(int, String, int, int");
        Student student = new UndergraduateStudent(id, name, grade, grade2);
        Validator validator = new Validator(this.storeService);
        ArrayList<String> errorList = validator.validateStudent(student);

        if (errorList.isEmpty()){
        	storeService.addElement(student);
        }
        return errorList;
    }

    public ArrayList<String> addStudent(int id, String name, int grade, int grade2, int grade3, String supervisor){
        logger.info("[Entering:]Controller.addStudent(int, String, int, int, int, String");
        Student student = new GraduateStudent(id, name, grade, supervisor, grade2, grade3);
        Validator validator = new Validator(this.storeService);
        ArrayList<String> errorList = validator.validateStudent(student);

        if (errorList.isEmpty()){
        	storeService.addElement(student);
        }
        return errorList;
    }

    public ArrayList<String> addStudent(int id, String name, int grade, int grade2, String supervisor, String thesis){
        logger.info("[Entering:]Controller.addStudent(int, String, int, int, String, String");
        Student student = new PhDStudent(id, name, grade, supervisor, thesis, grade2);
        Validator validator = new Validator(this.storeService);
        ArrayList<String> errorList = validator.validateStudent(student);

        if (errorList.isEmpty()){
        	storeService.addElement(student);
        }
        return errorList;
    }
    
    /**
     * 
     * Retrieves the entire student list from the repository.
     * 
     * @return A stack of students from the repository.
     */
    public ArrayList<String> allStudents() {
        logger.info("[Entering:]Controller.allStudents");
        return Controller.elementsFromMap(this.storeService.allElements());
    }

    public ArrayList<Student> allStudentObjects() {
        logger.info("[Entering:]Controller.allStudentObjects");
        ArrayList<Student> list = new ArrayList<Student>();

        for (Student student : this.storeService.allElements().values()) {
            list.add(student);
        }

        return list;
    }
    
    /**
     * 
     * Gets the number of students from the repository.
     * 
     * @return Total number of students in the repository.
     */
    public int numberOfStudents() {
        logger.info("[Entering:]Controller.numberOfStudents");
        return this.storeService.numberOfElements();
    }

    /**
     *
     * Computes the total number of Students greater than a given student
     * @param student The student to compare to.
     * @return The number of students greater than the given student.
     */
    public int numberOfStudentGreaterThan(Student student) {
        logger.info("[Entering:]Controller.numberOfStudentGreaterThan");
        Map<Integer, Student> allStudents = this.storeService.allElements();
        int no = 0;

        for (Comparable<Student> comparableStudent : allStudents.values()) {
            if (comparableStudent.isGreaterThan(student)) {
                no++;
            }
        }
        return no;
    }
}
