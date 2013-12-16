package com.company.Repository;

import com.company.Model.HasId;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Repository<T extends HasId> implements RepositoryInterface<T> {
    private Map<Integer, T> elements = new LinkedHashMap<Integer, T>();

    @Override
    public void addElement(T element) {
        this.elements.put(element.getId(), element);
    }

    @Override
    public ArrayList<T> getAllElements() {
        ArrayList<T> returnList = new ArrayList<T>();
        for (T element : elements.values()) {
            returnList.add(element);
        }

        return returnList;
    }

    @Override
    public void serializeToFile(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.elements);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deserializeFromFile(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.elements = (Map<Integer, T>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
