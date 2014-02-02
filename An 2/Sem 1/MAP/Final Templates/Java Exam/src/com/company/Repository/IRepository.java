package com.company.Repository;

import java.util.List;

/**
 * Created by C.Mihai on 01/02/14.
 */
public interface IRepository<T> {
    public void addElement(T element);
    public void removeElement(T element);
    public List<T> allElements();
}
