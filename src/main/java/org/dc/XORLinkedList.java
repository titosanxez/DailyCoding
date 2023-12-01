package org.dc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class XORLinkedList {
    private Node first;
    private Map<Integer, Node> memory = new HashMap<>();

    public XORLinkedList() {
    }

    public Node createNode() {
        return new Node();
    }

    public void add(Node node) {
        // Find last node, and check along the way the input node
        // is not already in the list
        var it = new Iterator(this.first);
        Node lastNode = null;
        int previousAddress = 0;
        while (it.hasNext()) {
            previousAddress = it.previousAddress;
            lastNode = it.next();
            if (lastNode == node) {
                throw new RuntimeException("Node already in list");
            }
        }

        if (lastNode == null) {
            this.first = node;
        } else {
            this.insert(node, lastNode, previousAddress);
        }
    }

    public void add(Node node, Node afterNode) {
        var it = new Iterator(this.first);
        // Find node and insert in between
        while (it.hasNext()) {
            int previousAddress = it.previousAddress;
            var next = it.next();
            if (next == afterNode) {
                this.insert(node, afterNode, previousAddress);
            }
        }
    }

    public Node last() {
        Node next = null;
        var it = new Iterator(this.first);
        while (it.hasNext()) {
            next = it.next();
        }

        return next;
    }

    public Node getAt(int index) {
        int count = 0;
        Node next = null;
        var it = new Iterator(this.first);
        while (it.hasNext() && count <= index) {
            next = it.next();
           ++count;
        }

        if (count < index) {
            throw new IndexOutOfBoundsException("Index=" + index + " is out of bounds");
        }

        return next;
    }

    private void insert(
            Node node,
            Node afterNode,
            int previousAddress) {

        Node next = this.next(afterNode, previousAddress);
        if (next == null) {
            afterNode.linkedNode ^= node.address;
            node.linkedNode = afterNode.address;
        } else {
            // unlink from next
            afterNode.linkedNode ^= next.address;
            next.linkedNode ^= afterNode.address;
            // Insert node
            afterNode.linkedNode ^= node.address;
            next.linkedNode ^= node.address;
            node.linkedNode = afterNode.address ^ next.address;
        }
    }

    private Node next(Node current, int previousAddress) {
        int nextAddress = previousAddress ^ current.linkedNode;
        return (nextAddress == 0)
                ? null
                : XORLinkedList.this.memory.get(nextAddress);
    }

    public Iterator iterator() {
        return new Iterator(this.first);
    }


    public class Iterator {
        private Node node;
        private int previousAddress;

        Iterator(Node firstNode) {
            this.node = firstNode;
            this.previousAddress = 0;
        }

        public boolean hasNext() {
            return (this.node != null);
        }

        public Node next() {
            if (!this.hasNext()) {
                return null;
            }

            var currentNode = this.node;
            // prepare next
            var nextNode = XORLinkedList.this.next(currentNode, this.previousAddress);
            this.previousAddress = currentNode.address;
            this.node = nextNode;

            return currentNode;
        }

    }

    public class Node {
        int address;
        int linkedNode;

        public Node() {
            this.address = (int) (Math.random() * Integer.MAX_VALUE);
            // Simulate memory of Nodes
            XORLinkedList.this.memory.put(this.address, this);
        }
    }
}
