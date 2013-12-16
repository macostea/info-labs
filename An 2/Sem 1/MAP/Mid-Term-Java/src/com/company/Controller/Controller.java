package com.company.Controller;

import com.company.Model.*;
import com.company.Repository.Repository;
import com.company.Repository.RepositoryInterface;

import java.util.ArrayList;

public class Controller {
    private RepositoryInterface<Element> repo = new Repository<Element>();

    public Controller(RepositoryInterface<Element> repo) {
        this.repo = repo;
    }

    public void addCD(int id, String color) {
        Element cd = new CD(id, color);
        repo.addElement(cd);
    }

    public void addDVD(int id, String color) {
        Element dvd = new DVD(id, color);
        repo.addElement(dvd);
    }

    public void addPen(int id, String color) {
        Element pen = new Pen(id, color);
        repo.addElement(pen);
    }

    public void addPhoto(int id, String color) {
        Element photo = new Photo(id, color);
        repo.addElement(photo);
    }

    public ArrayList<Element> getAllElementsWithColor(String color) {
        ArrayList<Element> returnList = new ArrayList<Element>();

        ArrayList<Element> allElements = this.repo.getAllElements();
        for (Element element : allElements) {
            if (element.color.equals(color)) {
                returnList.add(element);
            }
        }

        return returnList;
    }
}
