package com.company.Repository;

import com.company.Model.HasId;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author mihai
 */
public class Repository<T extends HasId> {
    protected Map<Integer, T> elements = new LinkedHashMap<Integer, T>();
    private static Logger logger = Logger.getLogger("Students");

    /**
     * Constructor
     */
    public Repository() {
        logger.info("[Entering:] Repository.init");
    }

    /**
     * Constructor
     *
     * @param adt The adt to use for the repository
     */
    public Repository(Map<Integer, T> adt) {
        logger.info("[Entering:] Repository.init(Map<Integer, T>)");
        this.elements = adt;
    }

    /**
     * 
     * Adds an element in the repository.
     * 
     * @param element The element to be added.
     */
    public void addElement(T element){
        this.elements.put(element.getId(), element);
        logger.info("[Entering:] Repository.addElement");
    }
    
    /**
     * 
     * Removes an element from the repository.
     * 
     * @param element The element to be removed.
     */
    public void removeElement(T element){
        this.elements.remove(element.getId());
        logger.info("[Entering:] Repository.removeElement");
    }
    
    /**
     * 
     * Returns the element from the top of the stack.
     * 
     * @return The element from the top of the stack.
     */
    public T getTopElement(){
        logger.info("[Entering:] Repository.getTopElement");
        for (T element : this.elements.values()) {
            return element;
        }

        return null;
    }
    
    /**
     * 
     * Returns the number of elements in the repository.
     * 
     * @return The number of elements in the repository. Positive int.
     */
    public int numberOfElements() {
        logger.info("[Entering:] Repository.numberOfElements");
        return this.elements.size();
    }
    
    /**
     * 
     * Returns a copy of the elements.
     * 
     * @return A copy of the elements.
     */
    public Map<Integer, T> allElements() {
        logger.info("[Entering:] Repository.allElements");
        Map<Integer, T> copy = new LinkedHashMap<Integer, T>();
        copy.putAll(this.elements);
        return copy;
    }

    /**
     *
     * Save the repository to a text file
     *
     * @param filename The name of the file in which to save.
     */
    public void saveRepoToFile(String filename) {
        logger.info("[Entering:] Repository.saveRepoToFile");
        Writer writer;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                                            new FileOutputStream(filename),
                                            "utf-8"));

            Map<Integer, T> copy = this.allElements();
            for (T element : copy.values()) {
                writer.write(element.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Serialize the repository to a file
     *
     * @param filename The file to save in.
     */
    public void serializeDataToFile(String filename) {
        logger.info("[Entering:] Repository.serializeDataToFile");
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

    /**
     *
     * Deserialize the repository from a file
     *
     * @param filename The file to read from.
     */
    public void deserializeDataFromFile(String filename) {
        logger.info("[Entering:] Repository.deserializeDataFromFile");
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Map<Integer, T> map = (Map<Integer, T>) in.readObject();
            this.elements = map;
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
