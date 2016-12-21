import java.util.Arrays;

/**
 * Created by alonso on 12/13/16.
 */
public class MergeBottomUp {

    public static Comparable[] aux;

    public static void merge(Comparable[] a, int lo,
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

    /** Sort using loop instead of recursion */
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz)
            for (int lo = 0; lo < N; lo += 2*sz)
                merge(a, lo, lo+sz-1, Math.min(N-1, lo+sz+sz-1));   //Most important part!
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
        StdOut.println("Beginning Merge Sort Bottom Up\n");
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
