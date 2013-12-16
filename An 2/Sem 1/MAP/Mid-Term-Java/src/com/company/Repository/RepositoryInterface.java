package com.company.Repository;

import java.util.ArrayList;

public interface RepositoryInterface<T> {
    void addElement(T element);
    ArrayList<T> getAllElements();
    void serializeToFile(String filename);
    void deserializeFromFile(String filename);
}
