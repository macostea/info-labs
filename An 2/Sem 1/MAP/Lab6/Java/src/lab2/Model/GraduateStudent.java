package lab2.Model;

public class GraduateStudent extends Student {
    public String supervisor;
    public int grade2;
    public int grade3;

    public GraduateStudent(int id, String name, int grade, String supervisor, int grade2, int grade3) {
        super(id, name, grade);
        this.supervisor = supervisor;
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

    @Override
    public String toString() {
        return String.format("%d|%s|%d|%d|%d|%s|", this.id, this.name, this.grade, this.grade2, this.grade3, this.supervisor);
    }
}
