import dsa.WeightedQuickUnionUF;
import stdlib.In;
import stdlib.StdOut;

public class Percolation {
    WeightedQuickUnionUF uf;
    boolean[][] perc;
    int openSites;
    int N;
    int in;
    int out;
    
    // Constructs an n x n percolation system, with all sites blocked.
    public Percolation(int n) {
        perc = new boolean [n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        openSites = 0;
        N = n;
        in = 0;
        out = N * N + 1;
        
        for (int i = 0; i != N + 1; i++) {
            uf.union(encode(in, i), in);
            uf.union(encode(N - 1, i), out);
        }
        
    }

    // Opens site (i, j) if it is not already open.
    public void open(int i, int j) {
        if (!isOpen(i, j)) {
            perc[i][j] = true;
            ++openSites;
            
            if (i > 0 && isOpen(i - 1, j))
                uf.union(encode(i, j), encode(i - 1, j));
            if (i < N - 1 && isOpen(i + 1, j))
                uf.union(encode(i, j), encode(i + 1, j));
            if (j > 0 && isOpen(i, j - 1))
                uf.union(encode(i, j), encode(i, j - 1));
            if (j < N - 1 && isOpen(i, j + 1))
                uf.union(encode(i, j), encode(i, j + 1));
        }
    }

    // Returns true if site (i, j) is open, and false otherwise.
    public boolean isOpen(int i, int j) {
        return perc[i][j];
    }

    // Returns true if site (i, j) is full, and false otherwise.
    public boolean isFull(int i, int j) {
        if (percolates())
            return true;
        return false;
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        return openSites;
    }

    // Returns true if this system percolates, and false otherwise.
    public boolean percolates() {
        return uf.connected(in, out);
    }

    // Returns an integer ID (1...n) for site (i, j).
    private int encode(int i, int j) {
        return i * N + j + 1;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.printf("%d x %d system:\n", n, n);
        StdOut.printf("  Open sites = %d\n", perc.numberOfOpenSites());
        StdOut.printf("  Percolates = %b\n", perc.percolates());
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.printf("  isFull(%d, %d) = %b\n", i, j, perc.isFull(i, j));
        }
    }
}
