package lab7.Model;

public class PhDStudent extends Student {
    public String supervisor;
    public String thesis;
    public int grade2;

    public PhDStudent() {
        super();
    }

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
        return (this.average() > student.average());
    }

    @Override
    public String toString() {
        return String.format("%s|%d|%s|%d|%d|%s|%s|", this.getClass().toString(), this.id, this.name, this.grade, this.grade2, this.supervisor, this.thesis);
    }

    @Override
    public void readAttributesFromString(String string) {
        String[] tokens = string.split("[|]+");
        this.id = Integer.parseInt(tokens[1]);
        this.name = tokens[2];
        this.grade = Integer.parseInt(tokens[3]);
        this.grade2 = Integer.parseInt(tokens[4]);
        this.supervisor = tokens[5];
        this.thesis = tokens[6];
    }
}
