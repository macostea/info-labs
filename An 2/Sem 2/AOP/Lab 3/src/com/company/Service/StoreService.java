package com.company.Service;

import java.util.Map;
import java.util.logging.Logger;

import com.company.Model.Student;
import com.company.Repository.Repository;

public class StoreService {
	private static Logger logger = Logger.getLogger("Students");
	private Repository<Student> repo;
	
	public StoreService(Repository<Student> repo) {
		this.repo = repo;
	}
	
	@ChangesSubject
	public void addElement(Student element) {
		this.repo.addElement(element);
	}
	
	public Map<Integer, Student> allElements() {
		return this.repo.allElements();
	}
	
	public int numberOfElements() {
		return this.repo.numberOfElements();
	}
}
