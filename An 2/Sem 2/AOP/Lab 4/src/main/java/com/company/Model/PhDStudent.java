package com.company.Model;

import java.util.logging.Logger;

public class PhDStudent extends Student {
    public String supervisor;
    public String thesis;
    public int grade2;
    private static Logger logger = Logger.getLogger("Students");

    public PhDStudent() {
        super();
    }

    public PhDStudent(int id, String name, int grade, String supervisor, String thesis, int grade2) {
        super(id, name, grade);
        logger.info("[Entering:]PhDStudent.init");
        this.supervisor = supervisor;
        this.thesis = thesis;
        this.grade2 = grade2;
    }

    @Override
    public float average() {
        logger.info("[Entering:]PhDStudent.average");
        return (this.grade + this.grade2) / 2;
    }

    @Override
    public boolean isGreaterThan(Student student) {
        logger.info("[Entering:]PhDStudent.isGreaterThan");
        return (this.average() > student.average());
    }

    @Override
    public String toString() {
        logger.info("[Entering:]PhDStudent.toString");
        return String.format("%s|%d|%s|%d|%d|%s|%s|", this.getClass().toString(), this.id, this.name, this.grade, this.grade2, this.supervisor, this.thesis);
    }

    @Override
    public void readAttributesFromString(String string) {
        logger.info("[Entering:]PhDStudent.readAttributesFromString");
        String[] tokens = string.split("[|]+");
        this.id = Integer.parseInt(tokens[1]);
        this.name = tokens[2];
        this.grade = Integer.parseInt(tokens[3]);
        this.grade2 = Integer.parseInt(tokens[4]);
        this.supervisor = tokens[5];
        this.thesis = tokens[6];
    }
}
