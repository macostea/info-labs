package com.company;

import com.company.Controller.Controller;
import com.company.Model.Element;
import com.company.Repository.Repository;
import com.company.Repository.RepositoryInterface;
import com.company.View.View;

public class Main {

    public static void main(String[] args) {
        RepositoryInterface<Element> repo = new Repository<Element>();
        Controller controller = new Controller(repo);
        View view = new View(controller);

        controller.addCD(0, "black");
        controller.addCD(1, "white");
        controller.addDVD(2, "blue");
        controller.addPen(3, "white");
        controller.addPhoto(4, "white");
        controller.addPen(5, "black");

        repo.serializeToFile("file.dat");
        repo.deserializeFromFile("file.dat");

        view.printAllWhiteElements();
    }
}
