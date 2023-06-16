/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {
    private int matrixWidth;
    private int imaginaryTopSite;
    private int imaginaryBottomSite;
    private int numOpenSites;
    private int[] parentID;
    private int[] connectedComponentSize;
    private int[] siteOpenness;

    // creates matrixWidth-by-matrixWidth grid, with all sites initially blocked
    public Percolation(int matrixWidth) {
        if (matrixWidth <= 0) {
            throw new IllegalArgumentException(
                    "Constructor: matrixWidth must be non-zero and positive.");
        }
        this.matrixWidth = matrixWidth;
        imaginaryTopSite = (matrixWidth * matrixWidth);
        imaginaryBottomSite = (matrixWidth * matrixWidth) + 1;
        numOpenSites = 0;

        parentID = new int[(matrixWidth * matrixWidth) + 2];
        connectedComponentSize = new int[(matrixWidth * matrixWidth) + 2];
        siteOpenness = new int[(matrixWidth * matrixWidth) + 2];

        for (int i = 0; i < (matrixWidth * matrixWidth) + 2; i++) {
            parentID[i] = i;
            connectedComponentSize[i] = 1;
        }

        for (int i = 0; i < matrixWidth; i++) {
            union(imaginaryTopSite, i);
        }
        for (int i = (matrixWidth * matrixWidth) - 1; i > matrixWidth * (matrixWidth - 1) - 1;
             i--) {
            union(imaginaryBottomSite, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        System.out.println("open(" + row + ", " + col + ")");
        if (invalidRowCol(row, col)) {
            throw new IllegalArgumentException("open(): Invalid row or column.");
        }
        int site = coords2Site(row, col);
        siteOpenness[site] = 1;
        numOpenSites += 1;

        // connect to adjacent sites if they are open
        if (!invalidRowCol(row - 1, col)) {
            if (isOpen(row - 1, col)) {
                this.union(site, coords2Site(row - 1, col));
            }
        }
        if (!invalidRowCol(row + 1, col)) {
            if (isOpen(row + 1, col)) {
                this.union(site, coords2Site(row + 1, col));
            }
        }
        if (!invalidRowCol(row, col - 1)) {
            if (isOpen(row, col - 1)) {
                this.union(site, coords2Site(row, col - 1));
            }
        }
        if (!invalidRowCol(row, col + 1)) {
            if (isOpen(row, col + 1)) {
                this.union(site, coords2Site(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (invalidRowCol(row, col)) {
            throw new IllegalArgumentException("isOpen(): Invalid row or column.");
        }
        return siteOpenness[coords2Site(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (invalidRowCol(row, col)) {
            throw new IllegalArgumentException("isFull(): Invalid row or column.");
        }
        int site = coords2Site(row, col);
        return connected(site, imaginaryTopSite);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return connected(imaginaryTopSite, imaginaryBottomSite);
    }

    public void print() {
        System.out.println(
                "Site:" + '\t' + "Parent:" + '\t' + "Root:" + '\t' + "Size:" + '\t' + "Open:");
        for (int i = 0; i < (matrixWidth * matrixWidth) + 2; i++) {
            if (i == matrixWidth * matrixWidth) {
                System.out.println("--- Imaginary Top Site ---");
            }
            if (i == matrixWidth * matrixWidth + 1) {
                System.out.println("--- Imaginary Bottom Site ---");
            }
            System.out.print(i);
            System.out.print('\t');
            System.out.print(parentID[i]);
            System.out.print('\t');
            System.out.print(root(i));
            System.out.print('\t');
            System.out.print(connectedComponentSize[i]);
            System.out.print('\t');
            System.out.println(siteOpenness[i]);
        }
    }

    private int root(int site) {
        while (parentID[site] != site) {
            site = parentID[site];
        }
        return site;
    }

    private boolean connected(int site1, int site2) {
        return root(site1) == root(site2);
    }

    private void union(int site1, int site2) {
        int root1 = root(site1);
        int root2 = root(site2);

        if (root1 == root2) {
            return; // Sites are already connected
        }

        // If connectedComponentSize of root1 and root2 are equal, root2 will become a child of root1
        if (connectedComponentSize[root1] < connectedComponentSize[root2]) {
            parentID[root1] = root2;
            connectedComponentSize[root2] += connectedComponentSize[root1];
        }
        else {
            parentID[root2] = root1;
            connectedComponentSize[root1] += connectedComponentSize[root2];
        }
    }

    // Takes in [row, col] pair and returns siteID
    private int coords2Site(int row, int col) {
        return (row * matrixWidth) + col;
    }

    // Takes in siteID and returns [row, col] pair
    private int[] site2Coords(int site) {
        int[] arr = new int[2];
        arr[0] = site / matrixWidth;    // row
        arr[1] = site % matrixWidth;    // col
        return arr;
    }

    private boolean invalidRowCol(int row, int col) {
        return ((row < 0) || (row >= matrixWidth) || (col < 0) || (col >= matrixWidth));
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        perc.print();
        perc.open(4, 1);
        System.out.println(perc.percolates());
        perc.open(3, 1);
        System.out.println(perc.percolates());
        perc.open(2, 1);
        System.out.println(perc.percolates());
        perc.open(1, 1);
        System.out.println(perc.percolates());
        perc.open(0, 1);
        System.out.println(perc.percolates());
        perc.print();
    }
}
