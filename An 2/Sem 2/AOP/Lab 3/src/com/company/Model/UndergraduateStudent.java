package com.company.Model;

import java.util.logging.Logger;

public class UndergraduateStudent extends Student {
    public int grade2;
    private static Logger logger = Logger.getLogger("Students");


    public UndergraduateStudent() {
        super();
    }

    public UndergraduateStudent(int id, String name, int grade, int grade2) {
        super(id, name, grade);
        logger.info("[Entering:]UndergraduateStudent.init");
        this.grade2 = grade2;
    }

    @Override
    public float average() {
        logger.info("[Entering:]UndergraduateStudent.average");
        return (this.grade + this.grade2) / 2;
    }

    @Override
    public boolean isGreaterThan(Student student) {
        logger.info("[Entering:]UndergraduateStudent.isGreaterThan");
        return (this.average() > student.average());
    }

    @Override
    public String toString() {
        logger.info("[Entering:]UndergraduateStudent.toString");
        return String.format("%s|%d|%s|%d|%d|", this.getClass().toString(), this.id, this.name, this.grade, this.grade2);
    }

    @Override
    public void readAttributesFromString(String string) {
        logger.info("[Entering:]UndergraduateStudent.readAttributesFromString");
        String[] tokens = string.split("[|]+");
        this.id = Integer.parseInt(tokens[1]);
        this.name = tokens[2];
        this.grade = Integer.parseInt(tokens[3]);
        this.grade2 = Integer.parseInt(tokens[4]);
    }
}
