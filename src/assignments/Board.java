import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Stack;

/**
 * Created by alonso on 2/25/17.
 * Class Board created as part of the 8 Puzzle problem's solution. Which will
 * use the A* algorithm.
 *
 */
public class Board {

    private final int n;
    private final int[][] blocks;

    /**
     * Constructor initializes the grid of blocks and the grid dimensions
     * @param blocks : 2d array containing the initial position
     */
    public Board(int[][] blocks) {
        n = blocks.length;
        this.blocks = new int[n][n];
        copyArray(blocks, this.blocks);
    }

    /** Helper array to copy one array to the other. Used in the constructor
     * @param arrA : Array to copy
     * @param arrB : Empty array which will get arrA's elements
     */
    private void copyArray(int[][] arrA, int[][] arrB) {
        for (int i = 0; i < arrA.length; i++)
            for (int j = 0; j < arrA.length; j++)
                arrB[i][j] = arrA[i][j];
    }

    /**
     * @return dimension of the puzzle
     */
    public int dimension() { return n; }

    /**
     * Method to determine the Hamming function (# of blocks in wrong position)
     * @return hamming distance
     */
    public int hamming() {
        int counter = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                if ((n*i + j + 1) < n*n && blocks[i][j] != (n*i + j + 1))
                    counter++;
            }
        return counter;
    }

    /**
     * Helper method for the manhattan distance. Returns distance of element in
     * i, j with respect to it's final (goal) position
     * @param i , j: coordinates in grid
     * @return Distance of element with respect to its final place.
     */
    private int helperManhattan(int i, int j) {
        int goalI = blocks[i][j]/n;
        int goalJ = (blocks[i][j]-1) %n;

        return Math.abs(goalI - i) + Math.abs(goalJ - j);
    }

    /**
     * Manhattan distance
     * @return Total manhattan distance
     */
    public int manhattan() {
        int counter = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                if ((n*i + j + 1) < n*n && blocks[i][j] != (n*i + j + 1))
                    counter += helperManhattan(i, j);
            }
        return counter;

    }

    /**
     * @return boolean whether this grid it's the goal (final) grid.
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * @return A twin board with 2 non zero elements swapped
     */
    public Board twin() {
        Board twinBoard = new Board(blocks);
        int temp;
        boolean swapped = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (twinBoard.blocks[i][j] != 0 && twinBoard.blocks[i][j + 1] != 0) {
                    temp = twinBoard.blocks[i][j];
                    twinBoard.blocks[i][j] = twinBoard.blocks[i][j + 1];
                    twinBoard.blocks[i][j + 1] = temp;
                    swapped = true;
                    break;
                }
            }
            if (swapped) break;
        }
        return twinBoard;
    }

    public boolean equals(Object y)  {
        // Check for reference equality
        if (y == this) return true;

        if (!(y instanceof Board)) return false;

        Board that = (Board) y;
        return (this.n == that.n) && (Arrays.deepEquals(this.blocks, that.blocks));
    }

    /**
     * Swap ellements in a board
     */
//    private static Board swapTwoElements(Board b, int i0, int j0, int i, int j) {
//        if ( i < 0 || i >= b.n || j < 0 || j >=b.n) return null;
//        int temp = b.blocks[i0][j0];
//        b.blocks[i0][j0] = b.blocks[i][j];
//        b.blocks[i][j] = temp;
//        return b;
//    }
    private Board swapTwoElements(Board b, int i0, int j0, int i, int j) {
//        if ( i < 0 || i >= b.n || j < 0 || j >=b.n) return null;
        int[][] tempB = new int[b.n][b.n];

        copyArray(b.blocks, tempB);
        int temp = tempB[i0][j0];
        tempB[i0][j0] = tempB[i][j];
        tempB[i][j] = temp;

        Board tempBoard = new Board(tempB);
        return tempBoard;
    }




    /**
     * Stack (or something that is iterable) of neighbors!
     * @return Something Iterable!
     */
    public Iterable<Board> neighbors() {
        int i0 = 0, j0 = 0;
        boolean foundBlank = false;

        // Find blank (0) in the board.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) {
                    i0 = i;
                    j0 = j;
//                    StdOut.println("Found 0!");
                    foundBlank = true;
                    break;
                }
            }
            if (foundBlank) break;
        }
//        StdOut.println("Found 0! at : " + i0 + " " + j0);

        Stack<Board> boardStack = new Stack<>();
        Board board = new Board(blocks);

        // Compute all the boards where 0 can move.
        // Cases:
        // if can move to the left, add i, j-1
        if (j0 > 0)
            boardStack.push(swapTwoElements(board, i0, j0, i0, j0-1));
//            boardStack.push(new Board(swapTwoElements(b, i0, j0, i0, j0-1)));

//        StdOut.println("Latest board after swap: \n" + board.toString());
        // if can move to the right, add i, j+1
        if (j0 < n-1)
            boardStack.push(swapTwoElements(board, i0, j0, i0, j0+1));
//            boardStack.push(new Board(swapTwoElements(b, i0, j0, i0, j0+1)));

        // if can move down, add i+1, j
        if (i0 < n-1)
            boardStack.push(swapTwoElements(board, i0, j0, i0+1, j0));
//            boardStack.push(new Board(swapTwoElements(b, i0, j0, i0+1, j0)));

        // if can move up, add i-1, j (swap)
        if (i0 > 0)
            boardStack.push(swapTwoElements(board, i0, j0, i0-1, j0));
//            boardStack.push(new Board(swapTwoElements(b, i0, j0, i0-1, j0)));

//        for (Board bs: boardStack) {
//            StdOut.println(bs.toString());
//        }
//        StdOut.println("Found 0! at : " + i0 + " " + j0);

        return boardStack;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                StdOut.print(blocks[i][j] + " ");
                s.append(blocks[i][j] + " ");
            }
//            StdOut.print("\n");
            s.append("\n");
        }
        return s.toString();
    }


    public static void main(String[] args) {
        int tempN = 3;
        int[] arr1 = {8, 1, 3, 4, 0, 2, 7, 6, 5};
        int[][] board1;

        StdOut.println("Array size: " + arr1.length);
        board1 = new int[tempN][tempN];

        for (int i = 0; i < tempN; i++)
            for (int j = 0; j < tempN; j++){
                board1[i][j] = arr1[tempN*i + j];
            }

        Board board = new Board(board1);
        StdOut.println("Board size: " + board.dimension());

        for (int i = 0; i < tempN; i++) {
            for (int j = 0; j < tempN; j++) {
                StdOut.print(board.blocks[i][j] + " ");
            }
            StdOut.print("\n");
        }
        StdOut.println("Hamming Distance: " + board.hamming());
        StdOut.println("Manhattan Distance: " + board.manhattan());
        StdOut.println("Is initial board goal?: " + board.isGoal());
//        StdOut.println("Twin board: " + board.twin());

//        for (int i = 0; i < tempN; i++) {
//            for (int j = 0; j < tempN; j++) {
//                StdOut.print(board.twin().blocks[i][j] + " ");
//            }
//            StdOut.print("\n");
//        }

//        StdOut.println("Twin board: \n" + board.twin().toString());
        StdOut.println("Neighbors\n");
        for (Board n: board.neighbors()) {
            StdOut.println(n.toString() + "\n");
        }

    }
}