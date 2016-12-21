import java.util.Arrays;

/**
 * Created by alonso on 12/13/16.
 * Merge Sort implementation using:
 *  - Recursion
 *  - Optimization (check if sorted, if so, return)
 *  - Comparable objects
 */
public class MergeSort {

    public static void merge(Comparable[] a, Comparable[] aux, int lo,
                             int mid, int hi)
    {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        /** Copy elements to aux array */
        for (int k = lo; k <=hi; k++)
            aux[k] = a[k];

        /** Merge first and second halves */
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
        {
            if      (i > mid)               a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);

    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        if (hi <= lo) return;
        int mid = (hi + lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (!less(a[mid+1], a[mid])) return;        // If already in order, return
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a)
    {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);

    }

    private static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi)
    {
        for (int i = 1 + lo; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /** Main method */
    public static void main(String[] args)
    {
//        int N = StdIn.readInt();
//        Double[] a = new Double[N];
        StdOut.println("Beginning\n");
//        for (int i = 0; i < N; i++)
//            a[i] = StdRandom.uniform();

//        Comparable[] a = {1, 3, 5, 7, 2, 4, 6, 8};
        Comparable[] a = {4, 5, 7, 1, 3, 2, 6, 8};
//        Comparable[] aux = new Comparable[a.length];

        StdOut.println("Array to sort: " + Arrays.toString(a));
        StdOut.println("Sorting\n");
        MergeSort.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}
