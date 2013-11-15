package lab2;

import java.util.ArrayList;

/**
 *
 * @author mihai
 */
public class Controller {
    private Repository repo;
    
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
    
    /**
     * 
     * Removes students from the repository until a student with grade == 0 is found.
     */
    public void removeStudentsUntilMaxGrade(){
        Student currentStudent = this.repo.getTopStudent();
        while (currentStudent.grade != 10){
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
    public Stack allStudents() {
        return this.repo.allStudents();
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
}
