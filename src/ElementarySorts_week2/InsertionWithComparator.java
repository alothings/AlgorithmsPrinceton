import java.util.Comparator;

/**
 * Created by alonso on 12/16/16.
 * Insertion Sort implementation using:
 *  - Comparators interface
 */
public class InsertionWithComparator
{

    public static void sort(Object[] a, Comparator comparator)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (less(comparator, a[j], a[j-1]))
                    exch(a, j, j-1);
                else break;
            }
        }
    }

    private static boolean less(Comparator c, Object v, Object w)
    { return c.compare(v, w) < 0; }

    private static void exch(Object[] a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparator comparator, Object[] a)
    {
        for (int i = 1; i < a.length; i++)
            if (less(comparator, a[i], a[i-1])) return false;
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
