/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {
    private int[][] grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new int[n][n];
        return;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        return;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] newGrid) {
        grid = newGrid;
        return;
    }

    // test client (optional)
    public static void main(String[] args) {
        /*
        StdOut.println("1");
        Percolation P1 = new Percolation(5);
        StdOut.println("2");
        int[][] g1 = P1.getGrid();
        */
        return;
    }
}
