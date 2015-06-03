using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2
{
    class Stack {
        private Student[] elements;
        private int capacity;
        private int size = 0;

        /*
         * Constructor
         * 
         * @param capacity Tha maximum capacity of the stack. Positive integer.
         */
        public Stack(int capacity)
        {
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
        public void push(Student student)
        {
            if (student == null) return;
            if (this.size == this.capacity)
            {
                Student[] temp = new Student[this.capacity];
                System.Array.Copy(this.elements, 0, temp, 0, this.capacity);
                this.capacity *= 2;
                this.elements = new Student[this.capacity];
                System.Array.Copy(temp, 0, this.elements, 0, this.capacity / 2);
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
        public Student pop()
        {
            if (this.size == 0) return null;
            this.size--;
            Student returnStudent = elements[this.size];
            elements[this.size] = null;
            return returnStudent;
        }

        /**
         * 
         * Returns the size of the stack.
         * 
         * @return The size of the stack. Positive int.
         */
        public int getSize()
        {
            return this.size;
        }

        /**
         * 
         * Checks if the stack is empty.
         * 
         * @return true if the stack is empty, false otherwise.
         */
        public Boolean isEmpty()
        {
            return (this.size == 0);
        }

        /**
         * 
         * Returns a copy of this stack.
         * 
         * @return A copy of this stack.
         */
        public Stack copy()
        {
            Stack copy = new Stack(this.size);
            System.Array.Copy(this.elements, 0, copy.elements, 0, this.size);
            copy.size = this.size;

            return copy;
        }
    }
}
