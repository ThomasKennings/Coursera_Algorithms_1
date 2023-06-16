/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int matrixWidth;
    private int numTrials;
    private double[] openFrac;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        matrixWidth = n;
        numTrials = trials;
        openFrac = new double[numTrials];

        int randomRow;
        int randomCol;
        for (int i = 0; i < numTrials; i++) {
            Percolation perc = new Percolation(matrixWidth);
            while (!perc.percolates()) {
                randomRow = StdRandom.uniformInt(matrixWidth);
                randomCol = StdRandom.uniformInt(matrixWidth);

                if (perc.isOpen(randomRow, randomCol)) {
                    // Only want to open a site if it's closed currently
                    continue;
                }

                perc.open(randomRow, randomCol);
            }

            openFrac[i] = 100.0 * perc.numberOfOpenSites() / (matrixWidth * matrixWidth);
            System.out.println("Percolated with openness fraction of " + openFrac[i] + "%.");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        // Use StdStats to compute this
        return 0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        // use StdStats to compute this
        return 0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

        return 0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

        return 0;
    }

    private void print() {
        System.out.println("mean" + '\t' + '\t' + '\t' + "= " + this.mean());
        System.out.println("stddev" + '\t' + '\t' + '\t' + "= " + this.stddev());
        System.out.println("95% confidence interval" + '\t' + "= [" + this.confidenceLo() + ", "
                                   + this.confidenceHi() + "]");
    }

    // test client (see below)
    public static void main(String[] args) {
        int matrixWidth = Integer.parseInt(args[0]);
        int numTrials = Integer.parseInt(args[1]);
        System.out.println("n: " + matrixWidth + '\t' + "T: " + numTrials);

        PercolationStats percStats = new PercolationStats(matrixWidth, numTrials);

        percStats.print();
    }

}
