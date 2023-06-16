/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int matrixWidth;
    private int imaginaryTopSite;
    private int imaginaryBottomSite;
    private WeightedQuickUnionUF ufObj;
    private int numOpenSites;
    private boolean[] siteOpen;

    // creates matrixWidth-by-matrixWidth grid, with all sites initially blocked
    public Percolation(int n) {
        matrixWidth = n;
        if (matrixWidth <= 0) {
            throw new IllegalArgumentException(
                    "Constructor: matrixWidth must be non-zero and positive.");
        }

        ufObj = new WeightedQuickUnionUF((matrixWidth * matrixWidth) + 2);

        imaginaryTopSite = (matrixWidth * matrixWidth);
        imaginaryBottomSite = (matrixWidth * matrixWidth) + 1;
        siteOpen = new boolean[(matrixWidth * matrixWidth) + 2];
        numOpenSites = 0;

        for (int i = 0; i < matrixWidth; i++) {
            ufObj.union(imaginaryTopSite, i);
        }

        for (int i = matrixWidth * (matrixWidth - 1); i < matrixWidth * matrixWidth; i++) {
            ufObj.union(imaginaryBottomSite, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // System.out.println("open(" + row + ", " + col + ")");
        if (invalidRowCol(row, col)) {
            throw new IllegalArgumentException("open(): Invalid row or column.");
        }

        if (isOpen(row, col)) {
            return;
        }

        int site = coords2Site(row, col);
        siteOpen[site] = true;
        numOpenSites += 1;

        // connect to adjacent sites if they are open
        if (!invalidRowCol(row - 1, col)) {
            if (isOpen(row - 1, col)) {
                ufObj.union(site, coords2Site(row - 1, col));
            }
        }
        if (!invalidRowCol(row + 1, col)) {
            if (isOpen(row + 1, col)) {
                ufObj.union(site, coords2Site(row + 1, col));
            }
        }
        if (!invalidRowCol(row, col - 1)) {
            if (isOpen(row, col - 1)) {
                ufObj.union(site, coords2Site(row, col - 1));
            }
        }
        if (!invalidRowCol(row, col + 1)) {
            if (isOpen(row, col + 1)) {
                ufObj.union(site, coords2Site(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (invalidRowCol(row, col)) {
            throw new IllegalArgumentException("isOpen(): Invalid row or column.");
        }

        return siteOpen[coords2Site(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (invalidRowCol(row, col)) {
            throw new IllegalArgumentException("isFull(): Invalid row or column.");
        }

        int site = coords2Site(row, col);

        return (ufObj.find(site) == ufObj.find(imaginaryTopSite)) && isOpen(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (matrixWidth == 1 && !isOpen(1, 1)) {
            return false;
        }

        return ufObj.find(imaginaryBottomSite) == ufObj.find(imaginaryTopSite);
    }

    // Takes in [row, col] pair and returns siteID
    private int coords2Site(int row, int col) {
        return ((row - 1) * matrixWidth) + col - 1;
    }

    private boolean invalidRowCol(int row, int col) {
        return ((row < 1) || (row > matrixWidth) || (col < 1) || (col > matrixWidth));
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        System.out.println(perc.percolates());
        perc.open(1, 1);
        perc.open(2, 1);
        perc.open(3, 1);
        perc.open(4, 1);
        perc.open(5, 1);
        System.out.println(perc.percolates());
    }
}
