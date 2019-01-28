/* *****************************************************************************
 *  Name: Arjun Bharadwaj
 *  Date: Sun Jan 27 2019
 *  Description: A Deque implementation using Arrays/LinkedLists.
 *  For our purposes, we will use a doubly-linked list with Nodes to represent it.
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node head, tail;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    } // End of node


    // Constructor
    public Deque() {
        size = 0;
        head = null;
        tail = null;
    } // End of constructor

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkNotNull(item);

        if (head == null) {
            head = new Node(item);
            tail = head;
        }
        else {
            Node temp = new Node(item);
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
        size += 1;
    }

    public void addLast(Item item) {
        checkNotNull(item);

        if (tail == null) {
            tail = new Node(item);
            head = tail;
        }
        else {
            Node temp = new Node(item);
            temp.prev = tail;
            tail.next = temp;
            tail = temp;
        }

        size += 1;
    }

    public Item removeFirst() {
        checkDequeEmpty();

        Node temp = head;
        temp.next = null;

        if (size() == 1) {
            head = null;
            tail = null;
        }
        else {
            head.next.prev = null;
            head = head.next;
        }

        size -= 1;
        return temp.item;
    }

    public Item removeLast() {
        checkDequeEmpty();

        Node temp = tail;
        temp.next = null;

        if (size() == 1) {
            head = null;
            tail = null;
        }
        else {
            tail.prev.next = null;
            tail = tail.prev;
        }

        size -= 1;
        return temp.item;
    }

    /********* ITERATOR METHODS **********/
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            /* Not supported */
            throw new UnsupportedOperationException("Deque iterator does not support remove.");
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("Can not call next when there are no more items!");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /********* HELPER METHODS **********/
    private void checkNotNull(Item item) {
        if (item == null)
            throw new IllegalArgumentException(
                    "Argument is null. Cannot insert a null element into the deque.");
    }

    private void checkDequeEmpty() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque is empty! Can not remove an element.");
        }
    }


    public static void main(String[] args) {
        // Client code.
        Deque<Integer> deq = new Deque<>();

        deq.addFirst(1);
        deq.addFirst(3);
        deq.addLast(5);

        Iterator<Integer> it = deq.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

