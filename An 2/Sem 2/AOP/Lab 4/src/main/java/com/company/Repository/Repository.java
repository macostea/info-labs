package com.company.Repository;

import java.util.Map;

import com.company.Model.Student;

/**
 *
 * @author mihai
 */
public interface Repository {
    /**
     * 
     * Adds an element in the repository.
     * 
     * @param element The element to be added.
     */
    public void addElement(Student element);
    
    /**
     * 
     * Returns the number of elements in the repository.
     * 
     * @return The number of elements in the repository. Positive int.
     */
    public int numberOfElements();
    
    /**
     * 
     * Returns a copy of the elements.
     * 
     * @return A copy of the elements.
     */
    public Map<Integer, Student> allElements();
}
