import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by alonso on 1/5/17.
 * This class will perform a series of computation experiments on Percolation
 *
 *
 public class PercolationStats {
     public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
     public double mean()                          // sample mean of percolation threshold
     public double stddev()                        // sample standard deviation of percolation threshold
     public double confidenceLo()                  // low  endpoint of 95% confidence interval
     public double confidenceHi()                  // high endpoint of 95% confidence interval

     public static void main(String[] args)        // test client (described below)
 }
 */
public class PercolationStats {
    private int trials;
    private double[] p;

    public PercolationStats(int n, int trials) {
        this.trials = trials;
        p = new double[trials];
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Please enter a valid number");
        }
        else {
            for (int i = 0; i < trials; i++) {
                Percolation grid = new Percolation(n);
                int row, col;
                while (!grid.percolates()) {
                    row = edu.princeton.cs.algs4.StdRandom.uniform(1, n+1);
                    col = StdRandom.uniform(1, n+1);
                    grid.open(row, col);
                }
                p[i] = (grid.numberOfOpenSites()*1.0)/(n*n);
            }
        }
    }
    public double mean() {
        double sum = 0.0;
        for (int i = 0; i < trials; i++) {
            sum += p[i];
        }
        return sum/trials;
    }
    public double stddev() {
        double m = mean();
        double sum = 0.0;
        for (int i = 0; i < trials; i++) {
            sum += (p[i] - m)*(p[i] - m);
        }
        return Math.sqrt(sum/(trials - 1));
    }
    public double confidenceLo() {
        return mean() - (1.96*stddev())/(Math.sqrt(trials));
    }
    public double confidenceHi() {
        return mean() + (1.96*stddev())/(Math.sqrt(trials));
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
//        StdOut.println("n: " + n + ", trials " + trials);

        PercolationStats pStats = new PercolationStats(n, trials);

        StdOut.println("mean                    = " + pStats.mean());
        StdOut.println("stddev                  = " + pStats.stddev());
        StdOut.println("95% confidence interval = " + pStats.confidenceLo()
                        + ", " + pStats.confidenceHi());

    }
}
