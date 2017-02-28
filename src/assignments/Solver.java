import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Stack;

/**
 * Created by alonso on 2/25/17.
 * Immutable data type
 */
public class Solver {

    private Board initialBoard;
    private boolean isSolvable = true;
    private int totalMoves;
    private SearchNode last;

    // Helper SearchNode class
    private class SearchNode implements Comparable<SearchNode>{
        Board board;
        int moves;
        SearchNode prev;
        private int priority;

        private SearchNode (Board b, int m, SearchNode p) {
            board = b;
            moves = m;
            prev = p;
            priority = b.manhattan() + moves;
        }

        public int compareTo(SearchNode other) {
            if (this.priority < other.priority) return -1;
            if (this.priority > other.priority) return 1;
            else return 0;
        }
    }

    /**
     * Find a solution to the initial board using A* algorithm
     * @param initial
     */
    public Solver (Board initial) {
        // Insert initial search node into a priority queue
        MinPQ<SearchNode> pq = new MinPQ<>();
        Board board = initial;
        int i = 0;

        pq.insert(new SearchNode(board, i, null));

        while (true)
        {
            // Delete node with min priority from PQ
            SearchNode node = pq.delMin();
//            StdOut.println("Solver inserting initial board \n" + board.toString());

            // repeat until goal board is found (dequeued)
            if (node.board.twin().isGoal()) {
                isSolvable = false;
                break;
            }
            if (node.board.isGoal()) {
                last = node;
                break;
            }

            i++;
            // Insert into PQ all neighboring nodes (except the nodes same as previous
            for (Board n : node.board.neighbors()) {

                if (node.prev == null || !n.equals(node.prev.board)) {
                    pq.insert(new SearchNode(n, i, node));
                }
            }
//            StdOut.println("Iteration: " + i);
        }
        totalMoves = i-1;
    }

    /**
     * Is the initial board solvable?
     */
    public boolean isSolvable() { return isSolvable; }

    /**
     * Min # of moves to solve initial board; -1 if unsolvable.
     * @return
     */
    public int moves() {
        if (isSolvable()) return totalMoves;
        return -1;
    }

    /**
     * Sequence of boards in shortest solution; null if unavailable
     * @return
     */
    public Iterable<Board> solution() {
        if (!isSolvable) return null;

        // stack of Boards from initial to goal board.
        Stack<Board> solution = new Stack<Board>();
        SearchNode s = last;
        while (s != null) {
            solution.push(s.board);
            s = s.prev;
        }
        return solution;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
