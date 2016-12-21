/**
 * Created by alonso on 12/16/16.
 */
public class QuickSort {
    /**
     * Partitions array so: "el < a[j]", a[j], "el > a[j]"
     * @param a: array of comparables
     * @param lo: initial position of array (0)
     * @param hi: last position of array (a.length - 1)
     * @return j: index of organized element.
     */
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
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
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
        Double[] a = new Double[N];
        StdOut.println("Beginning\n");
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();

        StdOut.println("Sorting\n");
        QuickSort.sort(a);
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
}
