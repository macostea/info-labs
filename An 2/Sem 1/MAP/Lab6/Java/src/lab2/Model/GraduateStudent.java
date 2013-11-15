package lab2.Model;

public class GraduateStudent extends Student implements ComparableStudent{
    public String supervisor;
    public int grade2;
    public int grade3;

    public GraduateStudent(int id, String name, int grade, String supervisor, int grade2, int grade3) {
        super(id, name, grade);
        this.grade2 = grade2;
        this.grade3 = grade3;
    }

    @Override
    public float average() {
        return (this.grade + this.grade2 + this.grade3) / 3;
    }

    @Override
    public boolean isGreaterThan(Student student) {
        return (this.average() > student.average());
    }
}
