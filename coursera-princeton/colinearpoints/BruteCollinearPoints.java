import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class BruteCollinearPoints {
    private List<LineSegment> segments = new LinkedList<>();

    /**
     * Finds all line segments containing 4 points
     * @param points
     */

    private void print(Point[] points) {
        for (Point point : points) {
            System.out.printf("%s, ", point.toString());
        }
        System.out.println();
    }

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Argument is null");
        for (Point p : points) if (p == null)
            throw new IllegalArgumentException("Argument contains null element");

        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        int length = sortedPoints.length;
        for (int i = 0; i < length -1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i+1]) == 0) {
                throw new IllegalArgumentException("Argument contains duplicate(s)");
            }
        }

        for (int i = 0; i < length - 3; i++) {
            Point p = sortedPoints[i];

            for (int j = i+1; j < length - 2; j++) {
                double referenceSlope = p.slopeTo(sortedPoints[j]);

                for (int k = j+1; k < length - 1; k++) {

                    if (referenceSlope == p.slopeTo(sortedPoints[k])) {
                        for (int l = k+1; l < length; l++) {
                            if (referenceSlope == p.slopeTo(sortedPoints[l])) {
                                segments.add( new LineSegment(p, sortedPoints[l]));
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] r = segments.toArray( new LineSegment[0]);
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In("input9.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        LineSegment[] segments = collinear.segments();
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
