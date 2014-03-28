package com.company.Utils.LinkedList;

import java.io.Serializable;

public class LinkedList<T> implements Serializable{
    public Node<T> firstNode;

    /**
     *
     * Returns the last node from the linked list
     * @return The last node from the linked list
     */
    public Node<T> getLastElement() {
        Node<T> currNode = firstNode;
        if (currNode != null) {
            while (currNode.next != null) {
                currNode = currNode.next;
            }
        }
        return currNode;
    }

    /**
     *
     * Removes a node from the linked list
     * @param node The node to be removed
     */
    public void removeNode(Node<T> node) {
        Node<T> currNode = firstNode;
        Node<T> nextNode = firstNode.next;

        if (this.firstNode == node) {
            this.firstNode = nextNode;
            return;
        }

        while (nextNode != null) {
            if (nextNode == node) {
                currNode.next = nextNode.next;
                currNode = nextNode;
                nextNode = nextNode.next;
            } else {
                currNode = nextNode;
                nextNode = nextNode.next;
            }
        }
    }

    /**
     *
     * Copies the entire linked list
     * @return The copy of the linked list
     */
    public LinkedList<T> copy() {
        LinkedList<T> copy = new LinkedList<T>();
        Node<T> currNodeCopy, nextNode, nextNodeCopy;
        if (this.firstNode != null) {
            Node<T> firstNodeCopy = new Node<T>(this.firstNode.data);
            copy.firstNode = firstNodeCopy;

            currNodeCopy = firstNodeCopy;

            nextNode = firstNode.next;

            while (nextNode != null) {
                nextNodeCopy = new Node<T>(nextNode.data);
                currNodeCopy.next = nextNodeCopy;
                currNodeCopy = nextNodeCopy;

                nextNode = nextNode.next;
            }
        }
        return copy;
    }
}
