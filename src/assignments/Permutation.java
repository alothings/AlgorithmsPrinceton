import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by alonso on 1/23/17.
 * Write a client program Permutation.java that takes a command-line
 * integer k; reads in a sequence of strings from standard input using
 * StdIn.readString(); and prints exactly k of them, uniformly at random.
 * Print each item from the sequence at most once. You may assume that
 * 0 ≤ k ≤ n, where n is the number of string on standard input.
 */
public class Permutation {

    public static void main(String[] args)
    {
        final int k = Integer.parseInt(args[0]);
        int counter1 = 0;
        int counter2 = 0;

        RandomizedQueue<String> items = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            if (k <= 0 ) break;
//            if (counter1 > 10 ) break;
//            if (counter1 >= k) {
//                items.dequeue();
//            }
            items.enqueue(StdIn.readString());
            counter1++;
        }

        for (int i = 0; i < k; i++)
            StdOut.println(items.dequeue());
    }
}
