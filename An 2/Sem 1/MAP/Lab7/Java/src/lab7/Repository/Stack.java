package lab7.Repository;

import lab7.Utils.LinkedList.LinkedList;
import lab7.Utils.LinkedList.Node;
import lab7.Utils.StackException;

/**
 *
 * @author mihai
 */
public class Stack<T> {
    private LinkedList<T> elements;
    private int size = 0;

    /**
     * 
     * Constructor.
     * 
     */
    public Stack(){
        elements = new LinkedList<T>();
    }
    
    /**
     * 
     * Pushes an element on top of the stack.
     * If the stack reached its capacity, then allocate more space.
     * 
     * @param data The data to be added to the stack.
     */
    public void push(T data) {
        Node<T> lastNode = this.elements.getLastElement();
        Node<T> nodeToAdd = new Node<T>(data);

        if (lastNode != null) {
            lastNode.next = nodeToAdd;
        } else {
            this.elements.firstNode = nodeToAdd;
        }
        size++;
    }
    
    /**
     * 
     * Returns the topmost element from the stack.
     * 
     * @return The topmost element from the stack.
     */
    public T pop() throws StackException{
        if (this.size == 0) {
            throw new StackException("Stack is empty!");
        }
        this.size--;
        Node<T> lastNode = this.elements.getLastElement();
        T returnData = lastNode.data;
        this.elements.removeNode(lastNode);
        return returnData;
    }
    
    /**
     * 
     * Returns the size of the stack.
     * 
     * @return The size of the stack. Positive int.
     */
    public int getSize(){
        return this.size;
    }
    
    /**
     * 
     * Checks if the stack is empty.
     * 
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }
    
    /**
     * 
     * Returns a copy of this stack.
     * 
     * @return A copy of this stack.
     */
    public Stack<T> copy() {
        Stack<T> copy = new Stack();
        copy.elements = this.elements.copy();
        copy.size = this.size;
        
        return copy;
    }
}
