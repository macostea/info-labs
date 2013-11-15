package lab2.Model;

/**
 *
 * @author mihai
 */
public class Student implements ComparableStudent {
    public String name;
    public int id;
    public int grade;

    public Student() {

    }
    
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
}
