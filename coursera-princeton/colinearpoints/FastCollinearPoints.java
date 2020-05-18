/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {
    private LineSegment[]  lineSegments;

    public FastCollinearPoints(Point[] points) {
        // Check that the argument is not null
        if (points == null) throw new IllegalArgumentException("points is null");
        if (containsNullPoint(points)) throw new IllegalArgumentException("Argument");

        LinkedList<LineSegment> segments = new LinkedList<>();
        // Sort the array before checking for duplicates
        Arrays.sort(points);

        // Check for duplicates
        if (containsDuplicates(points))
            throw new IllegalArgumentException("Argument cannot contain duplicate points");

        // Copy the points array
        int length = points.length;
        Point[] sortedPoints = Arrays.copyOf(points, length);
        // For each point currPoint, sort the other points by their slope order
        // to currPoint. Then find points which have the same slopeOrder.
        // These points will be colinear
        for (int i = 0; i < length - 1; i++) {
            Point currPoint = points[i];
            // System.out.printf("currPoint: %s\n", currPoint);

            // Use the current point's comparator to sort the other points
            // by their slope order to currPoint.
            // Comparator returns -infinity when the points compared are equal.
            // Since there are no duplicates, the only equal point is the point
            // itself. Therefore, the first point in sortedPoints is currPoint.
            Arrays.sort(sortedPoints, currPoint.slopeOrder());
            final double MIN = Double.MIN_VALUE;

            LinkedList<Point> candidates = new LinkedList<>();
            // From previous comment, we can ignore the currPoint by initialising j=1
            for (int j=1; j < length; ) {
                // list of possible colinear candidates.

                double referenceSlope = currPoint.slopeTo(sortedPoints[j]);
                candidates.add(sortedPoints[j++]);

                // Add points to candidates if they have the same
                // slope to currPoint. By virtue of sorting, these points will be
                // directly ahead of j.
                while(j < length && currPoint.slopeTo(sortedPoints[j]) == referenceSlope) {
                    // System.out.printf("adding %s\n", sortedPoints[j]);
                    candidates.add(sortedPoints[j++]);
                }
                if (candidates.size() >= 3 && currPoint.compareTo(candidates.peek()) < 0) {
                    Point endPoint = currPoint;
                    for (Point c : candidates) {
                        if (c.compareTo(endPoint) > 0) endPoint = c;
                    }

                    // System.out.printf("candidates.peek(): %s\n", candidates.peek());
                    // System.out.printf("candidates.removeLast() %s\n", candidates.removeLast());
                    // System.out.printf("endPoint %s", endPoint);
                    LineSegment lineSegment = new LineSegment(currPoint, endPoint);
                    // System.out.println(lineSegment);
                    segments.add(lineSegment);
                }
                candidates.clear();
            }
        }
        lineSegments = segments.toArray(new LineSegment[0]);
    }

    private boolean containsNullPoint(Point[] points) {
        for (Point p: points) {
            if (p == null) return true;
        }
        // If we arrive here, there are no null points
        return false;
    }
    private boolean containsDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++)
            if (points[i].compareTo(points[i+1]) == 0) return true;
        // If we arrive here, we have no duplicates
        return false;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In("input8.txt");
        int n = in.readInt();
        Point[] points = new Point[n];

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            StdDraw.circle(x, y, 100);
        }

        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        for (LineSegment segment : bruteCollinearPoints.segments()) {
            StdOut.println(segment);
        }

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
