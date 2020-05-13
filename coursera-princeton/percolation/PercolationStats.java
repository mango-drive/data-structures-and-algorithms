package percolation;
/* *****************************************************************************
 *  Name: Sebastien Corrigan
 *  Date: 25/10/2019
 *  Description: Monte Carlo simulation to estimate the percolation threshold of
 *  a grid of given size.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] results;
    private final double CONFIDENCE_95 = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException(
                "n is < 0"
        );
        if (trials <= 0) throw new IllegalArgumentException(
                "Trials is < 0"
        );

        results = new double[trials];

        for (int i = 0; i < trials; i++) {
            int count = 0;
            Percolation grid = new Percolation(n);

            while (!grid.percolates()) {
                int row, col;
                // Find a blocked site
                do {
                    row = StdRandom.uniform(n) + 1;
                    col = StdRandom.uniform(n) + 1;
                }
                while (grid.isOpen(row, col));

                grid.open(row, col);
                count++;
            }

            results[i] = count * 1.0 / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(results.length);
    }

    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(results.length);
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" +
                stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
