import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by alonso on 12/22/16.
 * Implementation of Max Priority Queue
 * Using:
 *  - Comparables
 *  - Resizable array
 */
public class MaxPQ<Key extends Comparable<Key>>
{
    private Key[] pq;
    private int N;

    public MaxPQ(int capacity)
    {
        pq = (Key[]) new Comparable[capacity+1];
        N = 0;
    }

    public boolean isEmpty()
    { return N == 0; }

    public int size()
    { return N; }

    public void insert (Key key)
    {
        // double size of array if necessary
        if (N >= pq.length -1) resize(2*pq.length);

        pq[++N] = key;
        swim(N);
    }

    public Key delMax()
    {
        Key max = pq[1];
        exch(1, N--);       // Swap with N and decrease N
        sink(1);
        pq[N+1] = null;
        return max;
    }

    /** Heap Helper functions *********
     *  Swim node up or sink accordingly
     * @param k: index in array
     */
    private void swim(int k)
    {
        while (k > 1 && less(k/2, k))
        {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k)
    {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (less(j, k)) break;
            exch(j, k);
            k = j;
        }
    }

    private void resize(int capacity)
    {
        assert capacity > N;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i=1; i <= N; i++)
            temp[i] = pq[i];
        pq = temp;
    }

    private boolean less(int i, int j)
    { return pq[i].compareTo(pq[j]) < 0; }

    private void exch(int i, int j)
    { Key t = pq[i]; pq[i] = pq[j]; pq[j] = t; }

    /**
     * Unit tests the MaxPQ data type
     * @param args the comand-line arguments
     */
    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>(10);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }

}
