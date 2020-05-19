
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
// java -cp algs4.jar Board.java
public class Board {
    private int[][] tiles;
    private int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.n = tiles.length;
    }

                                           
    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }



    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int tile = tiles[i][j];
                int correctPosition = i * n + j + 1;
                if (tile != 0 && tile != correctPosition) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int tile = tiles[i][j];
                if (tile != 0) {
                    int goal_i = (tile - 1) / n;
                    int goal_j = (tile - 1) % n;
                    manhattan += Math.abs(goal_i - i) + Math.abs(goal_j - j);
                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // // does this board equal y?
    // public boolean equals(Object y) {}

    // // all neighboring boards
    // public Iterable<Board> neighbors() {}

    // // a board that is obtained by exchanging any pair of tiles
    // public Board twin() {}

    // unit testing (not graded)
    public static void main(String[] args) {
        String filename = "puzzle04.txt";

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
        System.out.println(initial.hamming());
        System.out.println(initial.manhattan());
    }

}