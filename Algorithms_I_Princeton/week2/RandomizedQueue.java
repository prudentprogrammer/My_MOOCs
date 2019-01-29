/* *****************************************************************************
 *  Name: Arjun Bharadwaj
 *  Date: Mon Jan 28 2019
 *  Description: Randomized Queue implementation
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] s;

    public RandomizedQueue() {
        size = 0;
        s = (Item[]) new Object[1];

    } // End of constructor

    public boolean isEmpty() {
        return size == 0;
    } // End of method

    public int size() {
        return size;
    } // End of size function

    public void enqueue(Item item) {
        checkNotNull(item);
        s[size] = item;
        size += 1;
        if (size == s.length) // Then double the array capacity
            resize(2 * size);

    } // End of enqueue

    public Item dequeue() {
        checkDequeEmpty();

        // Get a uniform range number
        int r = StdRandom.uniform(size);
        Item it = s[r];
        s[r] = s[size - 1]; // Fill in the hole
        s[size - 1] = null;
        size -= 1;

        if (size > 0 && size == s.length / 4)
            resize(size / 2);

        return it;
    } // End of dequeue

    public Item sample() {
        checkDequeEmpty();
        int r = StdRandom.uniform(size);
        return s[r];
    } // End of sample


    /******* ITERATOR FUNCTIONS *******/
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int totalSize;
        private final int[] arr;

        public RandomizedQueueIterator() {
            totalSize = size;
            arr = new int[totalSize];
            for (int i = 0; i < totalSize; i++) {
                arr[i] = i;
            }
            StdRandom.shuffle(arr);
        }

        public boolean hasNext() {
            return totalSize > 0;
        }

        public void remove() {
            // Not supported
            throw new UnsupportedOperationException("This deque iterator does not support remove.");
        }

        public Item next() {
            if (totalSize == 0) {
                throw new NoSuchElementException("Can not call next when there are no more items!");
            }
            int lastIndex = arr[totalSize - 1];
            totalSize -= 1;
            return s[lastIndex];
        }

    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }


    /******* HELPER FUNCTIONS *******/
    private void resize(int newCapacity) {
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            copy[i] = s[i];
        s = copy; // Point the pointer to the new memory block
    }

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
        // Empty main function
    }
}
