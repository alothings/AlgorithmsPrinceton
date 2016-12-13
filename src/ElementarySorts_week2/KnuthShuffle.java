/**
 * Created by alonso on 12/13/16.
 * Implementation fo Knuth Shuffle
 *  - Shuffling in linear time
 */
public class KnuthShuffle {

    /**
     * Pick int between 0 and i
     * and swap
     */
    public static void shuffle(Object[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            int r = StdRandom.uniform(i + 1);
            exch(a, i, r);
        }
    }

//    private static boolean less(Comparable v, Comparable w)
//    { return v.compareTo(w) < 0; }

    private static void exch(Object[] a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args)
    {
        Object[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " " );

        shuffle(a);
        StdOut.print("\n" );
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " " );

    }

}
