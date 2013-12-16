package com.company.View;

import com.company.Controller.Controller;
import com.company.Model.Element;

import java.util.ArrayList;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void printAllWhiteElements() {
        ArrayList<Element> whiteElements = this.controller.getAllElementsWithColor("white");

        for (Element element : whiteElements) {
            System.out.println(element.toString());
        }
    }
}
