/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {
    private int gridDim;
    private int imaginaryTopSite;
    private int imaginaryBottomSite;
    private int numOpenSites;
    private int[] parentID;
    private int[] size;
    private int[] open;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        gridDim = n;
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

        for (int i = 0; i < gridDim; i++) {
            union(imaginaryTopSite, i);
        }
        for (int i = (gridDim * gridDim) - 1; i > gridDim * (gridDim - 1) - 1; i--) {
            union(imaginaryBottomSite, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        System.out.println("open(" + row + ", " + col + ")");
        int site = coords2Site(row, col);
        open[site] = 1;
        numOpenSites += 1;

        // union this site with adjacent open sites
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        System.out.println("isOpen(" + row + ", " + col + ")");
        return open[coords2Site(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int site = coords2Site(row, col);
        // return connected(site, imaginary_top_site);
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public void print() {
        System.out.println(
                "Site:" + '\t' + "Parent:" + '\t' + "Root:" + '\t' + "Size:" + '\t' + "Open:");
        for (int i = 0; i < (gridDim * gridDim) + 2; i++) {
            if (i == gridDim * gridDim) {
                System.out.println("--- Imaginary Top Site ---");
            }
            if (i == gridDim * gridDim + 1) {
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
        System.out.println("connected(" + site1 + ", " + site2 + ")");
        return root(site1) == root(site2);
    }

    private void union(int site1, int site2) {
        System.out.println("union(" + site1 + ", " + site2 + ")");
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
        return (row * gridDim) + col;
    }

    // Takes in siteID and returns [row, col] pair
    private int[] site2Coords(int site) {
        int[] arr = new int[2];
        arr[0] = site / gridDim;    // row
        arr[1] = site % gridDim;    // col
        return arr;
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(2);
        perc.print();
    }
}
