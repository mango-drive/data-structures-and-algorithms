
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
    }
                                           
    // string representation of this board
    public String toString() {
        String s = Integer.toString(tiles.length);
        return s;
    }

    // // board dimension n
    // public int dimension() {}

    // // number of tiles out of place
    // public int hamming() {}

    // // sum of Manhattan distances between tiles and goal
    // public int manhattan() {}

    // // is this board the goal board?
    // public boolean isGoal() {}

    // // does this board equal y?
    // public boolean equals(Object y) {}

    // // all neighboring boards
    // public Iterable<Board> neighbors() {}

    // // a board that is obtained by exchanging any pair of tiles
    // public Board twin() {}

    // unit testing (not graded)
    public static void main(String[] args) {
        String filename = "puzzle2x2-00.txt";

        // read in the board specified in the filename
        In in = new In(filename);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        // solve the slider puzzle
        Board initial = new Board(tiles);
        System.out.println(initial.toString());
    }

}