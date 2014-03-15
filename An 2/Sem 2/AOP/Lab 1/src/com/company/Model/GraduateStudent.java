package com.company.Model;

import java.util.logging.Logger;

public class GraduateStudent extends Student {
    public String supervisor;
    public int grade2;
    public int grade3;
    private static Logger logger = Logger.getLogger("Students");

    public GraduateStudent() {
        super();
    }

    public GraduateStudent(int id, String name, int grade, String supervisor, int grade2, int grade3) {
        super(id, name, grade);
        logger.info("[Entering:]GraduateStudent.init");
        this.supervisor = supervisor;
        this.grade2 = grade2;
        this.grade3 = grade3;
    }

    @Override
    public float average() {
        logger.info("[Entering:]GraduateStudent.average");
        return (this.grade + this.grade2 + this.grade3) / 3;
    }

    @Override
    public boolean isGreaterThan(Student student) {
        logger.info("[Entering:]GraduateStudent.isGreaterThan");
        return (this.average() > student.average());
    }

    @Override
    public String toString() {
        logger.info("[Entering:]GraduateStudent.toString");
        return String.format("%s|%d|%s|%d|%d|%d|%s|", this.getClass().toString(), this.id, this.name, this.grade, this.grade2, this.grade3, this.supervisor);
    }

    @Override
    public void readAttributesFromString(String string) {
        logger.info("[Entering:]GraduateStudent.readAttributesFromString");
        String[] tokens = string.split("[|]+");
        this.id = Integer.parseInt(tokens[1]);
        this.name = tokens[2];
        this.grade = Integer.parseInt(tokens[3]);
        this.grade2 = Integer.parseInt(tokens[4]);
        this.grade3 = Integer.parseInt(tokens[5]);
        this.supervisor = tokens[6];
    }
}
