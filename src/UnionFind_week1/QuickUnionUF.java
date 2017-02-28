import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by alonso on 12/10/16.
 * Lazy Approach to Union-Find problem (avoid doing work)
 * Idea: store as array of roots of connected trees
 * Weighted!
 */
public class QuickUnionUF {

    private int[] id;
    private int[] sz;

    public QuickUnionUF(int N){
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // chase parent pointers until reaching root
    private int root(int i){
        while (id[i] != i){
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    // check if p and q have the same root
    private boolean connected(int p, int q){
        return (root(p) == root(q));
    }

    // change root of p to point to root of q
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

    }
    public static void main(String[] args){

            int N = StdIn.readInt();
            QuickUnionUF uf = new QuickUnionUF(N);

            while (!StdIn.isEmpty()){
                int p = StdIn.readInt();
                int q = StdIn.readInt();
                if (!uf.connected(p, q)){
                    uf.union(p, q);
                    StdOut.println(p + " " + q);
                }
            }
    }
}
