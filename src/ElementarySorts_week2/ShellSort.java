import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by alonso on 12/13/16.
 * Implementation of Shell Sort
 */
public class ShellSort {

    public static void sort(Comparable[] a)
    {
        int N = a.length;

        int h = 1;
        while (h < N/3) h = 3*h + 1;    //1, 3, 13, 40, ...

        while (h >= 1)
        { // h-sorting the array
            for (int i = h; i < N; i++)
            {
                for (int j = i; j >= h && less(a[j], a[j-1]); j -= h)
                    exch(a, j, j-h);
            }
            h = h/3;
        }

    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public static void main(String[] args)
    {
        int N = StdIn.readInt();
        Double[] a = new Double[N];
        StdOut.println("Beginning\n");
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();

        StdOut.println("Sorting\n");
        ShellSort.sort(a);
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
}

/** Notes on Shell Sort - Created by shell in 1959
 * Idea: Moves entries more than one position at a time by:
 * h-sorting the array. (Similar to insertion but by an h factor)
 *
 * Why insertion sort?
 *  - Big increments -> small subarray
 *  - Small increments -> nearly in order.
 * Acceptable h factor is: 3x+1
 *
 * Used in hardware or embeded code because it is fast
 * for small or medium size arrays and its short to implement.
 *
 */
