package percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/* *****************************************************************************
 *  Name: Sebastien Corrigan
 *  Date: 25/10/2019
 *  Description: Percolation describes the situation where a system comprised
 *  of blocked and opened sites is able to pass a flow from one end to the
 *  other.
 *  For example: Given a porous material, would water be able to drain through
 *  it?
 *  Percolation occurs when there is a path of opened sites between one end of
 *  the system to the other.
 *
 *  This class the allows the user to define and maintain a grid of size
 *  n by n. A client can open sites one by one and test if the system
 *  percolates.
 **************************************************************************** */

/**
 * Percolation Class
 *  The grid is comprised of sites (cells) which can be:
 *      - open:     porous
 *      - blocked:  not porous
 *      - full:     connected to the top row via a chain of neighbouring sites
 *                  the chain is: left, right, up, down.
 *  Percolation: Given an n by n grid of blocked and open sites, the grid
 *  percolates if there is a full site in the bottom row.
 */
public class Percolation {
    private final int n;

    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF backwashUF;

    private final boolean[] isOpenFlags;

    private final int virtualTopIdx;
    private final int virtualBottomIdx;

    private int numberOfOpenedSites = 0;


    /**
     *  Create an n by n grid
     * @param n
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "Grid size must be greater than 0");
        }

        this.n = n;
        this.virtualTopIdx = n*n;
        this.virtualBottomIdx = n*n + 1;

        uf = new WeightedQuickUnionUF((n*n + 1));
        // n^2 sites + 2 virtual top and bottom sites
        backwashUF = new WeightedQuickUnionUF((n*n + 2));

        this.isOpenFlags = new boolean[n*n + 2];
        isOpenFlags[virtualTopIdx] = true;
        isOpenFlags[virtualBottomIdx] = true;
    }

    private boolean isValid(int row, int col) {
        return 0 < row && row <= n && 0 < col && col <= n;
    }

    public boolean isOpen(int row, int col) {
        if (isValid(row, col)) {
            return isOpenFlags[index(row, col)];
        } else {
            throw new IllegalArgumentException(
                    String.format("Cannot check isOpen, "
                                          + "%d, %d is not in bounds: %d",
                                  row, col, n));
        }
    }

    /**
     * Convert row and column (2D) into 1D.
     * 1D mapping is used to index the parent array of the WeightedQuickUnionUF
     * object.
     * Row and col indices follow the base 1 convention described in lectures
     * @param row row of current site: 1 <= row <= n
     * @param col col of current site: 1 <= col <= n
     * @return index of the site in a 1D matrix of length n^2
     */
    private int index(int row, int col) {
        if (isValid(row, col)) {
            return (row - 1) * n + col - 1;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("%d, %d is not in %d bounds", row, col, n));
        }
    }

    /**
     * Open a site and perform union with its open neighbours
     * Connect the site to virtual top/bottom sites if it is on the first/last
     * row
     * @param row row of the site to open
     * @param col col of the site
     */
    public void open(int row, int col) {
        if (!isValid(row, col)) throw new IllegalArgumentException();

        isOpenFlags[index(row, col)] = true;
        numberOfOpenedSites++;

        // Union with open neighbours
        int currIdx = index(row, col);

        if (row == 1) {
            backwashUF.union(currIdx, virtualTopIdx);
            uf.union(currIdx, virtualTopIdx);
        }
        if (row == n) {
            backwashUF.union(currIdx, virtualBottomIdx);
        }

        int[][] neighbourOffsets = {{+1, 0}, {0, -1}, {0, +1}, {-1, 0}};
        for (int[] offset : neighbourOffsets) {
            // Neighbour row and column
            int nRow = row + offset[0];
            int nCol = col + offset[1];

            if (isValid(nRow, nCol)) {
                if (isOpen(nRow, nCol)) {
                    int neighbourIdx = index(nRow, nCol);
                    backwashUF.union(currIdx, neighbourIdx);
                    uf.union(currIdx, neighbourIdx);
                }
            }
        }
    }

    /**
     * Is the site connected to the virtual top?
     * @param row row of the site
     * @param col col of the site
     * @return
     */
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) throw new IllegalArgumentException();
        return uf.connected(virtualTopIdx, index(row, col));
    }

    /**
     * Return a count of the number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        return numberOfOpenedSites;
    }

    /**
     * Does the grid percolate?
     * @return true if the gird percolates
     */
    public boolean percolates() {
        return backwashUF.connected(virtualTopIdx, virtualBottomIdx);
    }
}
