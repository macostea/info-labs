package com.company.Controller;

import com.company.Model.*;
import com.company.Repository.IRepository;
import com.company.Repository.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by C.Mihai on 01/02/14.
 */
public class Controller {
    private IRepository<Product> productRepo;

    public Controller(IRepository<Product> repo) {
        this.productRepo = repo;
    }

    public void addCoffee(String type, String servingType, int price) {
        Coffee coffee = new Coffee();
        coffee.type = type;
        coffee.servingType = servingType;
        coffee.price = price;
        this.productRepo.addElement(coffee);
    }

    public void addSandwich(int weight, String content, String servingType, int price) {
        Sandwich sandwich = new Sandwich();
        sandwich.weight = weight;
        sandwich.content = content;
        sandwich.servingType = servingType;
        sandwich.price = price;
        this.productRepo.addElement(sandwich);
    }

    public void addCake(String shape, String type, int price) {
        Cake cake = new Cake();
        cake.shape = shape;
        cake.type = type;
        cake.price = price;
        cake.servingType = "Cold";
        this.productRepo.addElement(cake);
    }

    public List<Product> filteredProducts(String servingType, int price) {
        List<Product> filteredProducts = new ArrayList<Product>();
        for (Product product : this.productRepo.allElements()) {
            if (product.servingType.equals(servingType) && product.price < price) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    public List<Product> allProducts() {
        return this.productRepo.allElements();
    }

    public void writeToFile(String filename) {
        Writer writer;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename),
                    "utf-8"));

            List<Product> copy = this.productRepo.allElements();
            for (Product product : copy) {
                writer.write(product.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
