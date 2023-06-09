/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String input = "";
        String output = "";
        double cycle = 0;

        System.out.println("Enter your words: ");

        while (!StdIn.isEmpty()) {
            cycle += 1;

            input = StdIn.readString();

            if (StdRandom.bernoulli(1 / cycle)) {
                output = input;
            }
        }
        StdOut.println(output);
    }
}
