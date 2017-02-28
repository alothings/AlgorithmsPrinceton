import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Created by alonso on 12/18/16.
 */
public class QuickSortWithDuplicates
{
    public static int partition(Comparable[] a, int lo, int hi)
{
        int i = lo, j = hi+1;
        while (true)
        {
            while (less(a[++i], a[lo]))        // find item on left to swap
                if (i == hi) break;

            while (less(a[lo], a[--j]))        // find item on right to swap
                if (j == lo) break;

            if (i >= j) break;                  // check if pointers cross. exit
            exch(a, i, j);                      // swap a[i] and a[j]
        }

        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);                   // To guarantee nlgn performance
        sort(a, 0, a.length -1);
    }

    public static void sort(Comparable[] a, int lo, int hi)
    {
        //Base case if hi greater than lo
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v =  a[lo];
        int i = lo;

        while(i <= gt)
        {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)        exch(a, lt++, i++);
            else if (cmp > 0)   exch(a, i, gt--);
            else                i++;
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    /**
     * Main method
     * @param args: user input, size of array
     */
    public static void main(String[] args)
    {
        int N = StdIn.readInt();
        Comparable[] a = new Comparable[N];
        StdOut.println("Beginning\n");
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0,10);

        System.out.println("Array: " + Arrays.toString(a) + "\n");
        StdOut.println("Sorting\n");
        QuickSortWithDuplicates.sort(a);
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
}
