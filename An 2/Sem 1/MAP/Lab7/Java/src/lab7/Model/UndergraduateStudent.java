package lab7.Model;

public class UndergraduateStudent extends Student {
    public int grade2;

    public UndergraduateStudent(int id, String name, int grade, int grade2) {
        super(id, name, grade);
        this.grade2 = grade2;
    }

    @Override
    public float average() {
        return (this.grade + this.grade2) / 2;
    }

    @Override
    public boolean isGreaterThan(Student student) {
        return (this.average() > student.average());
    }

    @Override
    public String toString() {
        return String.format("%d|%s|%d|%d|", this.id, this.name, this.grade, this.grade2);
    }
}
