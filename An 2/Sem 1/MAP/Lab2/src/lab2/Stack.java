package lab2;

/**
 *
 * @author mihai
 */
public class Stack {
    private Student[] elements;
    private int capacity;
    private int size = 0;

    /**
     * 
     * Constructor.
     * 
     * @param capacity The maximum capacity of the stack. Positive integer.
     */
    public Stack(int capacity){
        elements = new Student[capacity];
        this.capacity = capacity;
    }
    
    /**
     * 
     * Pushes a student on top of the stack.
     * If the stack reached its capacity, then allocate more space.
     * 
     * @param student The student to be added to the stack.
     */
    public void push(Student student){
        if (student == null) return;
        if (this.size == this.capacity) {
            Student[] temp = new Student[this.capacity];
            System.arraycopy(this.elements, 0, temp, 0, this.capacity);
            this.capacity *= 2;
            this.elements = new Student[this.capacity];
            System.arraycopy(temp, 0, this.elements, 0, this.capacity / 2);
        }
        this.elements[this.size] = student;
        this.size++;
    }
    
    /**
     * 
     * Returns the topmost element from the stack.
     * 
     * @return 
     */
    public Student pop(){
        if (this.size == 0) return null;
        this.size--;
        elements[this.size + 1] = null;
        return elements[this.size];
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
    public Stack copy() {
        Stack copy = new Stack(this.size);
        System.arraycopy(this.elements, 0, copy.elements, 0, this.size);
        copy.size = this.size;
        
        return copy;
    }
}
