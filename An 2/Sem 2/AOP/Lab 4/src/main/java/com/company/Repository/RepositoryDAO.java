package com.company.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.company.Model.GraduateStudent;
import com.company.Model.PhDStudent;
import com.company.Model.Student;
import com.company.Model.UndergraduateStudent;

public class RepositoryDAO extends JdbcDaoSupport implements Repository {
	public RepositoryDAO() {
		System.out.println("RepositoryDAO constructor");
	}
	
	 /**
     * 
     * Adds an element in the repository.
     * 
     * @param element The element to be added.
     */
    public void addElement(Student element){
    	String query = "INSERT INTO Students VALUES(" + element.id + ", '" + element.name + "', " + element.grade + ")";
    	getJdbcTemplate().update(query);
    	
    	if (element instanceof GraduateStudent) {
            String queryString = "INSERT INTO GraduateStudents VALUES(" + element.id + ", '" + ((GraduateStudent) element).supervisor + "', " + ((GraduateStudent) element).grade2 + ", " + ((GraduateStudent) element).grade3 + ")";
            getJdbcTemplate().update(queryString);
        } else if (element instanceof UndergraduateStudent) {
            String queryString = "INSERT INTO UndergraduateStudents VALUES(" + element.id + ", " + ((UndergraduateStudent) element).grade2 + ")";
            getJdbcTemplate().update(queryString);
        } else if (element instanceof PhDStudent) {
            String queryString = "INSERT INTO PhDStudents VALUES(" + element.id + ", '" + ((PhDStudent) element).supervisor + "', '" + ((PhDStudent) element).thesis + "', " + ((PhDStudent) element).grade2 + ")";
            getJdbcTemplate().update(queryString);
        }
    }
    
    /**
     * 
     * Returns the number of elements in the repository.
     * 
     * @return The number of elements in the repository. Positive int.
     */
    @SuppressWarnings("deprecation")
	public int numberOfElements() {
        return getJdbcTemplate().queryForInt("SELECT COUNT(*) FRIN Students");
    }
    
    /**
     * 
     * Returns a copy of the elements.
     * 
     * @return A copy of the elements.
     */
    public Map<Integer, Student> allElements() {
        List<Student> res = getJdbcTemplate().query("SELECT * FROM STUDENTS", new StudentMapper());
        Map<Integer, Student> map = new LinkedHashMap<Integer, Student>();
        for (Student student : res) {
        	map.put(student.getId(), student);
        }
        
        return map;
    }
    
    private class StudentMapper implements RowMapper<Student> {
    	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
    		int id = rs.getInt("id");
    		String name = rs.getString("name");
    		int grade = rs.getInt("grade");
    		String type = rs.getString("type");
    		if (type != null) {
	    		if (type.equals("phd")) {
	    			PhDStudent phd = getJdbcTemplate().queryForObject("SELECT * FROM PhDStudents where studentId=?", new PhDMapper(), id);
	    			phd.id = id;
	    			phd.name = name;
	    			phd.grade = grade;
	    			
	    			return phd;
	    		} else if (type.equals("grad")) {
	    			GraduateStudent grad = getJdbcTemplate().queryForObject("SELECT * FROM GraduateStudents where studentId=?", new GraduateMapper(), id);
	    			grad.id = id;
	    			grad.name = name;
	    			grad.grade = grade;
	    			
	    			return grad;
	    		} else if (type.equals("undergrad")) {
	    			UndergraduateStudent undergrad = getJdbcTemplate().queryForObject("SELECT * FROM UndergraduateStudents where studentId=?", new UndergraduateMapper(), id);
	    			undergrad.id = id;
	    			undergrad.name = name;
	    			undergrad.grade = grade;
	    			
	    			return undergrad;
	    		} else {
	    			Student student = new Student();
	    			student.id = id;
	    			student.name = name;
	    			student.grade = grade;
	    			
	    			return student;
	    		}
    		} else {
    			Student student = new Student();
    			student.id = id;
    			student.name = name;
    			student.grade = grade;
    			
    			return student;
    		}
    	}
    }
    
    private class GraduateMapper implements RowMapper<GraduateStudent> {
    	public GraduateStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
    		String supervisor = rs.getString("supervisor");
    		int grade2 = rs.getInt("grade2");
    		int grade3 = rs.getInt("grade3");
    		
    		GraduateStudent grad = new GraduateStudent();
    		grad.supervisor = supervisor;
    		grad.grade2 = grade2;
    		grad.grade3 = grade3;
    		
    		return grad;
    	}
    }
    
    private class UndergraduateMapper implements RowMapper<UndergraduateStudent> {
    	public UndergraduateStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
    		int grade2 = rs.getInt("grade2");
    		
    		UndergraduateStudent undergrad = new UndergraduateStudent();
    		undergrad.grade2 = grade2;
    		
    		return undergrad;
    	}
    }
    
    private class PhDMapper implements RowMapper<PhDStudent> {
    	public PhDStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
    		String supervisor = rs.getString("supervisor");
    		int grade2 = rs.getInt("grade2");
    		String thesis = rs.getString("thesis");
    		
    		PhDStudent phd = new PhDStudent();
    		phd.supervisor = supervisor;
    		phd.grade2 = grade2;
    		phd.thesis = thesis;
    		
    		return phd;
    	}
    }
}
