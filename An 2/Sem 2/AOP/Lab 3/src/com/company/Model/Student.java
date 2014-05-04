package com.company.Model;

import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author mihai
 */
public class Student implements Comparable<Student>, Readable, HasId, Buildable {
    public String name;
    public int id;
    public int grade;
    private static Logger logger = Logger.getLogger("Students");

    public Student() {

    }

    public Student(int id, String name, int grade){
        logger.info("[Entering:]Student.init");
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public float average() {
        logger.info("[Entering:]Student.average");
        return grade;
    }

    @Override
    public boolean isGreaterThan(Student student) {
        logger.info("[Entering:]Student.isGreaterThan");
        return (this.grade > student.grade);
    }

    @Override
    public String toString() {
        logger.info("[Entering:]Student.toString");
        return String.format("%s|%d|%s|%d|", this.getClass().toString(), this.id, this.name, this.grade);
    }

    @Override
    public void readAttributesFromString(String string) {
        logger.info("[Entering:]Student.readAttributesFromString");
        String[] tokens = string.split("[|]+");
        this.id = Integer.parseInt(tokens[1]);
        this.name = tokens[2];
        this.grade = Integer.parseInt(tokens[3]);
    }

    @Override
    public Integer getId() {
        logger.info("[Entering:]Student.getId");
        return this.id;
    }

    @Override
    public void buildObjectFromMap(Map<String, String> map) {
        logger.info("[Entering:]Student.buildObjectFromMap");
        this.id = Integer.parseInt(map.get("id"));
        this.name = map.get("name");
        this.grade = Integer.parseInt(map.get("grade"));
    }
}
