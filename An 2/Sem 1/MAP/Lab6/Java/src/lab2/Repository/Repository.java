package lab2.Repository;

import lab2.Model.ComparableStudent;
import lab2.Model.Student;
import lab2.Utils.StackException;

/**
 *
 * @author mihai
 */
public class Repository {
    private Stack<Student> students = new Stack<Student>();
    
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
        Stack<Student> temp = new Stack<Student>();
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
     * Return the number of student greater than a given student
     * @param student The student to compare to.
     * @return The number of students.
     */
    public int numberOfStudentsGreaterThan(Student student) {
        int no = 0;

        Stack<Student> copy = this.allStudents();
        while (!copy.isEmpty()) {
            try {
                ComparableStudent comparableStudent = copy.pop();
                if (comparableStudent.isGreaterThan(student)) {
                    no++;
                }
            } catch (StackException e) {
                e.printStackTrace();
            }
        }
        return no;
    }
    
    /**
     * 
     * Returns a copy of the students stack.
     * 
     * @return A copy of the students stack.
     */
    public Stack<Student> allStudents() {
        return this.students.copy();
    }
}
