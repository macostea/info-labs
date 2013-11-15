package lab2.Model;

public class UndergraduateStudent extends Student implements ComparableStudent {
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
}
