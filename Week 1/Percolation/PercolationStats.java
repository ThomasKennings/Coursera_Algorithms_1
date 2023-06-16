/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    // private static final int PERCENT_CONVERSION = 100;
    private int numTrials;
    private double[] openFrac;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException(
                    "PercolationStats: Matrix dimensions and number of trials must be positive, non-zero numbers.");
        }

        numTrials = trials;
        openFrac = new double[numTrials];

        int randomRow;
        int randomCol;
        for (int i = 0; i < numTrials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                randomRow = 1 + StdRandom.uniformInt(n);
                randomCol = 1 + StdRandom.uniformInt(n);

                if (perc.isOpen(randomRow, randomCol)) {
                    // Only want to open a site if it's closed currently
                    continue;
                }

                perc.open(randomRow, randomCol);
            }

            openFrac[i] = (double) perc.numberOfOpenSites() / (n * n);
            // System.out.println("Percolated with openness fraction of " + openFrac[i] + "%.");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openFrac);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openFrac);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(numTrials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(numTrials)));
    }

    private void print() {
        System.out.println("mean" + '\t' + '\t' + '\t' + "= " + this.mean());
        System.out.println("stddev" + '\t' + '\t' + '\t' + "= " + this.stddev());
        System.out.println("95% confidence interval" + '\t' + "= [" + this.confidenceLo() + ", "
                                   + this.confidenceHi() + "]");
    }

    // test client (see below)
    public static void main(String[] args) {
        // Stopwatch stopwatch = new Stopwatch();

        int matrixWidth = Integer.parseInt(args[0]);
        int numTrials = Integer.parseInt(args[1]);

        PercolationStats percStats = new PercolationStats(matrixWidth, numTrials);

        percStats.print();
        // System.out.println("Elapsed time: " + stopwatch.elapsedTime() + " seconds.");
    }
}
