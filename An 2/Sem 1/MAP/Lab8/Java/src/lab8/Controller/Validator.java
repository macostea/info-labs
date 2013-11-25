package lab8.Controller;

import lab8.Model.Student;
import lab8.Repository.Repository;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author mihai
 */
public class Validator {
    final String kNameEmptyError = "Name can not be empty";
    final String kDuplicateId = "Id must be unique";
    final String kInvalidGrade = "Grade is an int between 1 and 10";
    
    private Repository repo;
    private ArrayList<String> errorList;

    /**
     * 
     * Constructor.
     * 
     * @param repo The repository to search in.
     */
    public Validator(Repository repo) {
        this.errorList = new ArrayList<String>();
        this.repo = repo;
    }
    
    /**
     * 
     * Validate a student.
     * 
     * @param student The student to be validated.
     * @return A list of error messages. If list is empty then the student is valid.
     */
    public ArrayList<String> validateStudent(Student student) {
        this.validateId(student.id);
        this.validateName(student.name);
        this.validateGrade(student.grade);
        
        return this.errorList;
    }
    
    /**
     * 
     * Checks if an id is already stored in the repository.
     * 
     * @param id The id to be checked.
     */
    private void validateId(int id) {
        Map<Integer, Student> students = this.repo.allElements();
        
        boolean found = false;

        for (Student currStudent : students.values()) {
            if (currStudent.id == id) {
                found = true;
                break;
            }
        }
        
        if (found) this.errorList.add(this.kDuplicateId);
    }
    
    /**
     * 
     * Checks if the student name is an empty string.
     * 
     * @param name The name to be checked.
     */
    private void validateName(String name) {
        if (name.isEmpty()) {
            this.errorList.add(this.kNameEmptyError);
        }
    }
    
    /**
     * 
     * Checks if a grade is between 1 and 10.
     * 
     * @param grade The grade to be checked.
     */
    private void validateGrade(int grade) {
        if (grade > 10 || grade < 0) {
            this.errorList.add(this.kInvalidGrade);
        }
    }
}
