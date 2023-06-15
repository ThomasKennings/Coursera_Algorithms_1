/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {
    private int n;
    private int imaginaryTopSite;
    private int imaginaryBottomSite;
    private int numOpenSites;
    private int[] parentID;
    private int[] size;
    private int[] open;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Constructor: n must be non-zero and positive.");
        }
        this.n = n;
        imaginaryTopSite = (n * n);
        imaginaryBottomSite = (n * n) + 1;
        numOpenSites = 0;

        parentID = new int[(n * n) + 2];
        size = new int[(n * n) + 2];
        open = new int[(n * n) + 2];

        for (int i = 0; i < (n * n) + 2; i++) {
            parentID[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            union(imaginaryTopSite, i);
        }
        for (int i = (n * n) - 1; i > n * (n - 1) - 1; i--) {
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
        open[site] = 1;
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
        // System.out.println("isOpen(" + row + ", " + col + ")");
        if (invalidRowCol(row, col)) {
            throw new IllegalArgumentException("isOpen(): Invalid row or column.");
        }
        return open[coords2Site(row, col)] == 1;
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
        // System.out.println("Percolates()");
        return connected(imaginaryTopSite, imaginaryBottomSite);
    }

    public void print() {
        System.out.println(
                "Site:" + '\t' + "Parent:" + '\t' + "Root:" + '\t' + "Size:" + '\t' + "Open:");
        for (int i = 0; i < (n * n) + 2; i++) {
            if (i == n * n) {
                System.out.println("--- Imaginary Top Site ---");
            }
            if (i == n * n + 1) {
                System.out.println("--- Imaginary Bottom Site ---");
            }
            System.out.print(i);
            System.out.print('\t');
            System.out.print(parentID[i]);
            System.out.print('\t');
            System.out.print(root(i));
            System.out.print('\t');
            System.out.print(size[i]);
            System.out.print('\t');
            System.out.println(open[i]);
        }
    }

    private int root(int site) {
        while (parentID[site] != site) {
            site = parentID[site];
        }
        return site;
    }

    private boolean connected(int site1, int site2) {
        // System.out.println("connected(" + site1 + ", " + site2 + ")");
        return root(site1) == root(site2);
    }

    private void union(int site1, int site2) {
        // System.out.println("union(" + site1 + ", " + site2 + ")");
        int root1 = root(site1);
        int root2 = root(site2);

        if (root1 == root2) {
            return; // Sites are already connected
        }

        // If size of root1 and root2 are equal, root2 will become a child of root1
        if (size[root1] < size[root2]) {
            parentID[root1] = root2;
            size[root2] += size[root1];
        }
        else {
            parentID[root2] = root1;
            size[root1] += size[root2];
        }
    }

    // Takes in [row, col] pair and returns siteID
    private int coords2Site(int row, int col) {
        return (row * n) + col;
    }

    // Takes in siteID and returns [row, col] pair
    private int[] site2Coords(int site) {
        int[] arr = new int[2];
        arr[0] = site / n;    // row
        arr[1] = site % n;    // col
        return arr;
    }

    private boolean invalidRowCol(int row, int col) {
        return ((row < 0) || (row >= n) || (col < 0) || (col >= n));
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
