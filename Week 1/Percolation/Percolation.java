/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {
    private int N;
    private int[] parentID;
    private int[] size;
    private int[] open;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        N = n;

        parentID = new int[n * n];
        size = new int[n * n];
        open = new int[n * n];

        for (int i = 0; i < (n * n); i++) {
            parentID[i] = i;
            size[i] = 1;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // union this site with adjacent open sites
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
    
    public void print() {
        System.out.println("Site:" + '\t' + "Parent:" + '\t' + "Root:" + '\t' + "Size:");
        for (int i = 0; i < (N * N); i++) {
            System.out.print(i);
            System.out.print('\t');
            System.out.print(parentID[i]);
            System.out.print('\t');
            System.out.print(root(i));
            System.out.print('\t');
            System.out.println(size[i]);
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

        if (size[root1] > size[root2]) {
            parentID[root2] = root1;
            size[root1] += size[root2];
        }
        else {
            parentID[root1] = root2;
            size[root2] += size[root1];
        }
    }

    private int coords2Site(int row, int col) {
        return (row * N) + col;
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(4);
        perc.print();
        System.out.println("Union(0, 1)");
        perc.union(0, 1);
        perc.print();
        System.out.println("Union(1, 3)");
        perc.union(1, 3);
        perc.print();
    }
}
