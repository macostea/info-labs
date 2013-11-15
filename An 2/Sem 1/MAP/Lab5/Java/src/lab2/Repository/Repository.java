package lab2.Repository;

import lab2.Model.Student;
import lab2.Utils.StackException;

/**
 *
 * @author mihai
 */
public class Repository {
    private Stack students = new Stack(100);
    
    /**
     * 
     * Adds a student in the repository.
     * 
     * @param student The student to be added.
     */
    public void addStudent(Student student){
        this.students.push(student);
    }
    
    /**
     * 
     * Removes a student from the repository.
     * 
     * @param student The student to be removed.
     */
    public void removeStudent(Student student) throws StackException{
        Stack temp = new Stack(this.students.getSize());
        while (true) {
            Student element = this.students.pop();
            if (element.equals(student)){
                break;
            }
            temp.push(element);
        }
        while (temp.getSize() != 0) {
            this.students.push(temp.pop());
        }
    }
    
    /**
     * 
     * Returns the student from the top of the stack.
     * 
     * @return The student from the top of the stack.
     */
    public Student getTopStudent() throws StackException {
        Student temp = this.students.pop();
        this.students.push(temp);
        return temp;
    }
    
    /**
     * 
     * Returns the number of students in the repository.
     * 
     * @return The number of students in the repository. Positive int.
     */
    public int numberOfStudents() {
        return this.students.getSize();
    }
    
    /**
     * 
     * Returns a copy of the students stack.
     * 
     * @return A copy of the students stack.
     */
    public Stack allStudents() {
        return this.students.copy();
    }
}
