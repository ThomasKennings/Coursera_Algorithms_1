/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {
    // Open = 1, closed = 0
    private int[][] grid;
    private int gridSize;
    private int numOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        gridSize = n;
        grid = new int[n][n];
        numOpenSites = 0;

        // initialize site ID, parent node, and size arrays
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        grid[row][col] = 1;
        numOpenSites += 1;

        // union this site with adjacent open sites
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return (grid[row][col] == 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // "A full site is an open site that can be connected to an open site in the top row via a
        // chain of neighboring (left, right, up, down) open sites."
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // "We say the system percolates if there is a full site in the bottom row. In other words,
        // a system percolates if we fill all open sites connected to the top row and that process
        // fills some open site on the bottom row."
        return false;
    }

    public void print() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print('\n');
        }
    }

    /*
    private union([row1, col1], [row2, col2]) {
        // convert [row, col] pairs to site IDs

        // if size(site1) > size(site2)
        //      site2.parent = site1.root
        // else
        //      site1.parent = site2.root

        // total size = size(site1) + size(site2)
        // change size of each element in CC to total size


    }
     */

    /*
    private rowCol2SiteID(row, col) {
        // siteID = row*gridSize + col
    }
     */

    public static void main(String[] args) {
        Percolation perc = new Percolation(10);
        System.out.println("Opening...");
        perc.open(0, 0);
        perc.open(1, 0);
        perc.open(2, 2);
        perc.print();
    }
}
