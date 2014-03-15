package com.company.Repository;

import com.company.Model.*;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

/**
 * Created by C.Mihai on 14/03/14.
 */
public class DBRepository extends Repository<Student> {
    private DBConnection connection;
    private String tableName;
    private static Logger logger = Logger.getLogger("Students");

    public DBRepository(String tableName) {
        logger.info("[Entering:] DBRepository.init");
        this.connection = new DBConnection();
        this.tableName = tableName;

        this.populateRepo();
    }

    public void populateRepo() {
        logger.info("[Entering:] DBRepository.populateRepo");
        try {
            Connection connection1 = this.connection.getConnection();
            ResultSet resultSet = DBConnection.getTable(connection1, tableName);
            while (resultSet.next()) {
                Student student = new Student();
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                map.put("id", resultSet.getString(1));
                map.put("name", resultSet.getString(2));
                map.put("grade", resultSet.getString(3));

                student.buildObjectFromMap(map);

                try {
                    String query = "SELECT * FROM GraduateStudents WHERE studentId = " + resultSet.getString(1);
                    Statement stmt = null;
                    stmt = connection1.createStatement();
                    ResultSet graduates = stmt.executeQuery(query);
                    if (graduates.next()) {
                        GraduateStudent graduateStudent = new GraduateStudent();
                        graduateStudent.id = student.id;
                        graduateStudent.name = student.name;
                        graduateStudent.grade = student.grade;
                        graduateStudent.supervisor = graduates.getString(2);
                        graduateStudent.grade2 = graduates.getInt(3);
                        graduateStudent.grade3 = graduates.getInt(4);

                        this.elements.put(graduateStudent.id, graduateStudent);
                        continue;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    String query = "SELECT * FROM PhDStudents WHERE studentId = " + resultSet.getString(1);
                    Statement stmt = null;
                    stmt = connection1.createStatement();
                    ResultSet phds = stmt.executeQuery(query);
                    if (phds.next()) {
                        PhDStudent phdStudent = new PhDStudent();
                        phdStudent.id = student.id;
                        phdStudent.name = student.name;
                        phdStudent.grade = student.grade;
                        phdStudent.supervisor = phds.getString(2);
                        phdStudent.grade2 = phds.getInt(4);
                        phdStudent.thesis = phds.getString(3);

                        this.elements.put(phdStudent.id, phdStudent);
                        continue;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    String query = "SELECT * FROM UndergraduateStudents WHERE studentId = " + resultSet.getString(1);
                    Statement stmt = null;
                    stmt = connection1.createStatement();
                    ResultSet undergrads = stmt.executeQuery(query);
                    if (undergrads.next()) {
                        UndergraduateStudent undergraduateStudent = new UndergraduateStudent();
                        undergraduateStudent.id = student.id;
                        undergraduateStudent.name = student.name;
                        undergraduateStudent.grade = student.grade;
                        undergraduateStudent.grade = undergrads.getInt(2);

                        this.elements.put(undergraduateStudent.id, undergraduateStudent);
                        continue;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                this.elements.put(student.id, student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addElement(Student element) {
        logger.info("[Entering:] DBRepository.addElement");
        super.addElement(element);
        String query = "INSERT INTO Students VALUES(" + element.id + ", '" + element.name + "', " + element.grade + ")";
        try {
            Statement stmt = connection.getConnection().createStatement();
            stmt.executeUpdate(query);

            if (element instanceof GraduateStudent) {
                String queryString = "INSERT INTO GraduateStudents VALUES(" + element.id + ", '" + ((GraduateStudent) element).supervisor + "', " + ((GraduateStudent) element).grade2 + ", " + ((GraduateStudent) element).grade3 + ")";
                Statement statement = connection.getConnection().createStatement();
                statement.executeUpdate(queryString);
            } else if (element instanceof UndergraduateStudent) {
                String queryString = "INSERT INTO UndergraduateStudents VALUES(" + element.id + ", " + ((UndergraduateStudent) element).grade2 + ")";
                Statement statement = connection.getConnection().createStatement();
                statement.executeUpdate(queryString);
            } else if (element instanceof PhDStudent) {
                String queryString = "INSERT INTO PhDStudents VALUES(" + element.id + ", '" + ((PhDStudent) element).supervisor + "', '" + ((PhDStudent) element).thesis + "', " + ((PhDStudent) element).grade2 + ")";
                Statement statement = connection.getConnection().createStatement();
                statement.executeUpdate(queryString);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
