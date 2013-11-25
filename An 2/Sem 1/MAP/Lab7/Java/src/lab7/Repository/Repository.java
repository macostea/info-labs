package lab7.Repository;

import lab7.Utils.StackException;

import java.io.*;

/**
 *
 * @author mihai
 */
public class Repository<T> {
    private Stack<T> elements = new Stack<T>();

    /**
     * Constructor
     */
    public Repository() {
    }

    /**
     * Constructor
     *
     * @param adt The adt to use for the repository
     */
    public Repository(Stack<T> adt) {
        this.elements = adt;
    }

    /**
     * 
     * Adds an element in the repository.
     * 
     * @param element The element to be added.
     */
    public void addElement(T element){
        this.elements.push(element);
    }
    
    /**
     * 
     * Removes an element from the repository.
     * 
     * @param element The element to be removed.
     */
    public void removeElement(T element) throws StackException{
        Stack<T> temp = new Stack<T>();
        while (true) {
            T topElement = this.elements.pop();
            if (element.equals(topElement)){
                break;
            }
            temp.push(topElement);
        }
        while (temp.getSize() != 0) {
            this.elements.push(temp.pop());
        }
    }
    
    /**
     * 
     * Returns the element from the top of the stack.
     * 
     * @return The element from the top of the stack.
     */
    public T getTopElement() throws StackException {
        T temp = this.elements.pop();
        this.elements.push(temp);
        return temp;
    }
    
    /**
     * 
     * Returns the number of elements in the repository.
     * 
     * @return The number of elements in the repository. Positive int.
     */
    public int numberOfElements() {
        return this.elements.getSize();
    }
    
    /**
     * 
     * Returns a copy of the elements stack.
     * 
     * @return A copy of the elements stack.
     */
    public Stack<T> allElements() {
        return this.elements.copy();
    }

    /**
     *
     * Save the repository to a text file
     *
     * @param filename The name of the file in which to save.
     */
    public void saveRepoToFile(String filename) {
        Writer writer;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                                            new FileOutputStream(filename),
                                            "utf-8"));

            Stack<T> copy = this.allElements();
            while (!copy.isEmpty()) {
                T element = copy.pop();
                writer.write(element.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StackException e) {
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
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Stack<T> stack = (Stack<T>) in.readObject();
            this.elements = stack;
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
