package lab2.Model;

public class PhDStudent extends Student implements ComparableStudent{
    public String supervisor;
    public String thesis;
    public int grade2;

    public PhDStudent(int id, String name, int grade, String supervisor, String thesis, int grade2) {
        super(id, name, grade);
        this.supervisor = supervisor;
        this.thesis = thesis;
        this.grade2 = grade2;
    }

    @Override
    public float average() {
        return (this.grade + this.grade2) / 2;
    }

    @Override
    public boolean isGreaterThan(Student student) {
        return (this.average() > this.average());
    }
}
