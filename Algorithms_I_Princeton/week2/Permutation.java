/* *****************************************************************************
 *  Name: Arjun Bharadwaj
 *  Date: Mon Jan 28 2019
 *  Description: Generates random permutation of k words.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> strings = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            strings.enqueue(StdIn.readString());
        }

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            StdOut.println(strings.dequeue());
        } // End of main
    } // End of main
} // End of class.
