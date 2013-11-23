package lab7.Model;

import java.io.Serializable;

/**
 *
 * @author mihai
 */
public class Student implements Comparable<Student>, Readable<Student>, Serializable {
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

    @Override
    public String toString() {
        return String.format("%s|%d|%s|%d|", this.getClass().toString(), this.id, this.name, this.grade);
    }

    @Override
    public void readAttributesFromString(String string) {
        String[] tokens = string.split("[|]+");
        this.id = Integer.parseInt(tokens[1]);
        this.name = tokens[2];
        this.grade = Integer.parseInt(tokens[3]);
    }
}
