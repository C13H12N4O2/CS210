import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.StdStats;

public class PercolationStats {
    double[] list;
    int M;

    // Performs m independent experiments on an n x n percolation system.
    public PercolationStats(int n, int m) {
        list = new double[m];
        M = m;
        
        for (int i = 0; i != m; ++i) {
            Percolation PF = new Percolation(n);
            while (!PF.percolates()) {
                int a = StdRandom.uniform(0, PF.N),
                    b = StdRandom.uniform(0, PF.N);
                if(!PF.isOpen(a, b))
                    PF.open(a, b);
            } list[i] = (double) PF.openSites / Math.pow(n, 2);
        }
    }

    // Returns sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(list);
    }

    // Returns sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(list);
    }

    // Returns low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(M);
    }

    // Returns high endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(M);
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, m);
        StdOut.printf("Percolation threshold for a %d x %d system:\n", n, n);
        StdOut.printf("  Mean                = %.3f\n", stats.mean());
        StdOut.printf("  Standard deviation  = %.3f\n", stats.stddev());
        StdOut.printf("  Confidence interval = [%.3f, %.3f]\n", stats.confidenceLow(),
                stats.confidenceHigh());
    }
}

