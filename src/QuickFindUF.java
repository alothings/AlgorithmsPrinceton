
/**
 * Created by alonso on 12/10/16.
 * Quick Find: A solution to Union-Find problem (Dynamic Connectivity)
 * Runtime: O(n) for initializing, and O(n) for union.
 * If joining all nodes, then it's O(n^2)
 */


public class QuickFindUF {
    private int[] id;

    // Sets id of each object to itself - Starting point
    public QuickFindUF(int N){
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    //check whether p and q are the same component
    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    //union
    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == pid)
                id[i] = qid;
        }
    }

    public static void main(String[] args){

        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);

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
