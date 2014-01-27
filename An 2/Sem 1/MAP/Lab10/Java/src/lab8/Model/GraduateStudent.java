package lab8.Model;

public class GraduateStudent extends Student {
    public String supervisor;
    public int grade2;
    public int grade3;

    public GraduateStudent() {
        super();
    }

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
        return String.format("%s|%d|%s|%d|%d|%d|%s|", this.getClass().toString(), this.id, this.name, this.grade, this.grade2, this.grade3, this.supervisor);
    }

    @Override
    public void readAttributesFromString(String string) {
        String[] tokens = string.split("[|]+");
        this.id = Integer.parseInt(tokens[1]);
        this.name = tokens[2];
        this.grade = Integer.parseInt(tokens[3]);
        this.grade2 = Integer.parseInt(tokens[4]);
        this.grade3 = Integer.parseInt(tokens[5]);
        this.supervisor = tokens[6];
    }
}
