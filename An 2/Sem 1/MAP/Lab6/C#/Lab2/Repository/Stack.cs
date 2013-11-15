using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab2.Lab2_Model;
using Lab2.Lab2_Utils;
using Lab2.Lab2_Utils.Lab2_LinkedList;

namespace Lab2
{
	namespace Lab2_Repository {
	    class Stack<T> {
			private Lab2.Lab2_Utils.Lab2_LinkedList.LinkedList<T> elements;
			private int size = 0;

			/**
		     * 
		     * Constructor.
		     * 
		     */
			public Stack(){
				elements = new Lab2.Lab2_Utils.Lab2_LinkedList.LinkedList<T>();
			}

	        /**
	         * 
	         * Pushes a student on top of the stack.
	         * If the stack reached its capacity, then allocate more space.
	         * 
	         * @param student The student to be added to the stack.
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
	         * @return 
	         */
	        public T pop()
	        {
				if (this.size == 0) {
					throw new StackException ("Stack is empty!");
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
			public Stack<T> copy() {
				Stack<T> copy = new Stack<T>();
				copy.elements = this.elements.copy();
				copy.size = this.size;

				return copy;
			}
	    }
	}
}
