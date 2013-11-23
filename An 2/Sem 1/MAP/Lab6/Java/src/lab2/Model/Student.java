package lab2.Model;

/**
 *
 * @author mihai
 */
public class Student implements Comparable<Student> {
    public String name;
    public int id;
    public int grade;
    
    public Student(int id, String name, int grade){
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public float average() {
        return grade;
    }

    @Override
    public boolean isGreaterThan(Student student) {
        return (this.grade > student.grade);
    }

    @Override
    public String toString() {
        return String.format("%d|%s|%d|", this.id, this.name, this.grade);
    }
}
