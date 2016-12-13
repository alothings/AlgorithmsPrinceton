/**
 * Created by alonso on 12/13/16.
 * Implementation of Insertion Sort
 * Linear time O(N) in partially sorted arrays
 */
public class Insertion {

    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (less(a[j], a[j-1]))
                    exch(a, j, j-1);
                else break;
            }
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
        Insertion.sort(a);
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
}


/**
 * Notes on Insertion Sort:
 * Runtime Best case    :   N - 1 compares and 0 exchanges (ascending)
 * Runtime Avg case     :   ~ 1/4 N^2 compares and ~ 1/4 N2 exchanges
 * Runtime Best case    :   1/2 N^2 Compares and !/2 N2 exchanges (descending)
 *
 * In partially sorted arrays ( Number of inversions is constant)
 * Insertion runs in O(N)
 */