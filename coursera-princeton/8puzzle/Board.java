
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList;

public class Board {
    private int[][] tiles;
    private int n;
    private int blank_i, blank_j;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.n = tiles.length;
        setBlankCoordinates();
    }

    private void setBlankCoordinates() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    blank_i = i;
                    blank_j = j;
                    return;
                }
            }
        }
    }

    public String blankCoordinates() {
        return Integer.toString(blank_i) + ", " + Integer.toString(blank_j);
    }

                                           
    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
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

    // does this board equal y?
    public boolean equals(Object y) {
        if (y instanceof Board) {
            Board b2 = (Board) y;

            if (y.toString().equals(this.toString())) 
            {
                return true;
            }
        }
        return false;
    }

    private boolean isValidIndex(int i, int j) {
        return i >= 0 && i < n 
            && j >= 0 && j < n;
    }

    
    private int[][] deepCopy() {
        int[][] tiles_copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles_copy[i][j] = tiles[i][j];
            }
        }
        return tiles_copy;
    }

    private void swap(int[][] a, int i, int j, int x, int y) {
        int temp = a[i][j];
        a[i][j] = a[x][y];
        a[x][y] = temp;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        LinkedList<Board> neighbours = new LinkedList<Board>();
        int[][] directions = {
            {1, 0},  // north
            {0, 1},  // east
            {-1, 0}, // south
            {0, -1}  // west
        };

        for (int[] d : directions) {
            int x = d[0];
            int y = d[1];

            if (isValidIndex(x, y)) {
                int[][] new_tiles = this.deepCopy();
                swap(new_tiles, blank_i, blank_j, x, y);
                neighbours.add(new Board(new_tiles));
            }
        }

        return neighbours;
    }

    // // a board that is obtained by exchanging any pair of tiles
    // public Board twin() {}

    // unit testing (not graded)
    private static int[][] read(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        return tiles;
    }
    

    // java -cp algs4.jar Board.java
    public static void main(String[] args) {
        String filename = "puzzle04.txt";
        int[][] tiles = Board.read(filename);

        // solve the slider puzzle
        Board initial = new Board(tiles);
        System.out.println(initial.toString());
        System.out.println(initial.hamming());
        System.out.println(initial.manhattan());

        System.out.println("Testing equality");
        Object initial_2 = (Object) new Board(tiles);
        System.out.println(initial.equals(initial_2));

        filename = "puzzle4x4-00.txt";
        int[][] tiles2 = Board.read(filename);
        Board notEqual = new Board(tiles2);
        System.out.println(initial.equals(tiles2));

        System.out.println("Testing valid index");
        System.out.println(initial.isValidIndex(1, 0));
        System.out.println(initial.isValidIndex(1, 1));
        System.out.println(initial.isValidIndex(0, 1));

        System.out.println(initial.isValidIndex(-1, 0));
        System.out.println(initial.isValidIndex(5, 0));
        System.out.println(initial.isValidIndex(-1, -1));
        System.out.println(initial.isValidIndex(10, 5));
        System.out.println(initial.isValidIndex(1, 10));

        System.out.println("Blank Square");
        System.out.println(initial.toString());
        System.out.println(initial.blankCoordinates());
        System.out.println(notEqual.toString());
        System.out.println(notEqual.blankCoordinates());

        System.out.println("Neighbours");
        for (Board b : initial.neighbors()) {
            System.out.println(b.toString());
        }
    }
}