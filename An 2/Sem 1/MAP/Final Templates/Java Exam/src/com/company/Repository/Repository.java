package com.company.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class Repository<T> implements IRepository<T> {
    private ArrayList<T> elements = new ArrayList<T>();

    public Repository() {

    }

    public Repository(ArrayList<T> list) {
        this.elements = list;
    }

    @Override
    public void addElement(T element) {
        this.elements.add(element);
    }

    @Override
    public void removeElement(T element) {
        this.elements.remove(element);
    }

    @Override
    public List<T> allElements() {
        return new ArrayList<T>(this.elements);
    }

}
