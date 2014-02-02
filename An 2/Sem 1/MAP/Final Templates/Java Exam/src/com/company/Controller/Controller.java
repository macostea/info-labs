package com.company.Controller;

import com.company.Model.*;
import com.company.Repository.IRepository;
import com.company.Repository.Repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class Controller {
    private IRepository<Product> productRepo;

    public void addProduct() {
        this.productRepo.addElement(new Bagel());
    }

    public void readFromFile(String filename) {
        BufferedReader reader;
        ArrayList<Product> products = new ArrayList<Product>();

        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filename),
                    "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("[,]+");
                Product element = null;
                if (tokens[2].contains("Bread")) {
                    element = new Bread();
                } else if (tokens[2].contains("Bagel")) {
                    element = new Bagel();
                } else if (tokens[2].contains("Cake")) {
                    element = new Cake();
                }

                element.readAttributesFromString(line);

                products.add(element);
            }
            this.productRepo = new Repository<Product>(products);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
