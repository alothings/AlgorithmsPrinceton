/**
 * Created by alonso on 1/5/17.
 * Program to estimate the value of the *Percolation Threshold*
 * via Monte Carlo simulation
 *
 * Percolation. Given a composite systems comprised of randomly distributed
 * insulating and metallic materials: what fraction of the materials need
 * to be metallic so that the composite system is an electrical conductor?
 *
 * MODEL a percolation system using an n-by-n grid of sites
 * - Each site is either open or blocked.
 * - Full site is an open site that can be connected to an open site in the
 * top row via a chain of neighboring (left, right, up, down) open sites
 * - Percolates if: there is a *full site* in the bottom row
 *
 * PROBLEM: if sites are independently set to be open with probability p
 * what is the probability the system percolates?
 * Thus: if p = 0 -> P(percolates) = 0
 *       if p = 1 -> System percolates
 * If N is large, then there is a threshold p* such that if p<p* then
 * system almost ever percolates.
 *
 * TASK: write a program to estimate p*
 *
 *
 * Percolation data type. To model a percolation system, create a data type
 * Percolation with the following API:

 public class Percolation {
     public Percolation(int n)                // create n-by-n grid, with all sites blocked
     public    void open(int row, int col)    // open site (row, col) if it is not open already
     public boolean isOpen(int row, int col)  // is site (row, col) open?
     public boolean isFull(int row, int col)  // is site (row, col) full?
     public     int numberOfOpenSites()       // number of open sites
     public boolean percolates()              // does the system percolate?

     public static void main(String[] args)   // test client (optional)
 }

 union(), find(), connected(), and count().

 so.. Open sites and check if system percolates?
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private boolean[][] grid;
    private int virtualTop = 0;                // virtual top site
    private int virtualBottom;                 // virtual bottom site
    private int size;
    private WeightedQuickUnionUF qf;
    private WeightedQuickUnionUF qfIsFull;

    /**
     * Percolation constructor. Initialize all sites blocked
     * @param n
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Please enter a valid number");
        }
        size = n;
        virtualBottom = size*size + 1;
        grid = new boolean[n][n];
        qf = new WeightedQuickUnionUF(n*n + 2);
        qfIsFull = new WeightedQuickUnionUF(n*n + 1);

//        for (int i = 1; i <= size; i++) qf.union(virtualTop, i);
//        for (int i = size*size; i > size*size - size; i--) qf.union(virtualBottom, i);
    }

    /**
     * Connects site to other open sites next to it
     * @param i : row of site to open but assuming matrix is from 1 -> n
     * @param j : col fo site to open
     */
    public void open(int i, int j) {
        int row = i - 1;
        int col = j - 1;

        grid[row][col] = true;
        int index = size*row + col + 1;

        if (col > 0 && grid[row][col-1]) {
            qf.union(index - 1, index);
            qfIsFull.union(index - 1, index);
        } // left

        if (col < size -1  && grid[row][col+1]) {
            qf.union(index + 1, index);
            qfIsFull.union(index + 1, index);
        } // right

        if (i == size) qf.union(index, virtualBottom);
        if (row > 0) {
            if (grid[row-1][col]) {
                qf.union(index, index - size);
                qfIsFull.union(index, index - size);
            }
        } // above

        if (i == 1) {
            qf.union(index, virtualTop);
            qfIsFull.union(index, virtualTop);
        }
        if (row < size - 1) {
            if (grid[row+1][col]) {
                qf.union(index, index + size);
                qfIsFull.union(index, index + size);
            }
        } // below

//        if (row > 0 && grid[row-1][col]) {
//            qf.union(index, index - size);
//            if (i == size) qf.union(index, virtualBottom);
//        }

//        if (row < size -1 && grid[row+1][col]) {
//            qf.union(index, index + size);
//            if (i == 1) qf.union(index, virtualTop);
//        }

    }

    public boolean isOpen(int row, int col) {
        return grid[row-1][col-1];
    }

    public boolean isFull(int i, int j) {
        if (i <= 0 || j <= 0 || i > size || j > size) {
            throw new IndexOutOfBoundsException("Please enter a valid number");
        }
        int row = i - 1;
        int col = j - 1;
        int index = size*row + col + 1;
        return qfIsFull.connected(virtualTop, index);
    }

    public int numberOfOpenSites() {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j]) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean percolates() {
        return qf.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {
        int n = edu.princeton.cs.algs4.StdIn.readInt();
        // Initialize all sites to be blocked
        Percolation grid = new Percolation(n);

        /**
         * Testing Percolation
         */
        edu.princeton.cs.algs4.StdOut.println("Percolates?: " + grid.percolates());
        edu.princeton.cs.algs4.StdOut.println("Number of open sites: " + grid.numberOfOpenSites());

        // Repeat until percolates:
        // 1. Choose site uniformly at random among blocked sites
        // 2. Open site
        int row, col;
        int counter = 0;
        while (!StdIn.isEmpty() && counter < 6) {
            counter += 1;
            row = StdIn.readInt();
            col = StdIn.readInt();
            grid.open(row, col);
            StdOut.println("is open? " + grid.isOpen(row, col));
            StdOut.println("is full? " + grid.isFull(row, col));
//            if (grid.percolates()) break;
        }

        edu.princeton.cs.algs4.StdOut.println("Percolates?: " + grid.percolates());
        edu.princeton.cs.algs4.StdOut.println("Number of open sites: " + grid.numberOfOpenSites());


        // The fraction of sites that are open when system percolates is p*
//        double p = (grid.numberOfOpenSites()*1.0)/(n*n);
//        StdOut.println("p* -> " + p);
    }

}















