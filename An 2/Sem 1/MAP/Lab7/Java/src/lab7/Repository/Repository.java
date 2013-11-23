package lab7.Repository;

import lab7.Utils.StackException;

/**
 *
 * @author mihai
 */
public class Repository<T> {
    private Stack<T> elements = new Stack<T>();
    
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
}
