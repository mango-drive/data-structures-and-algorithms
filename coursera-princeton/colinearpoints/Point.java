/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that == null) throw new NullPointerException("slopeTo() argument was null");
        if (isEqualTo(that))  return Double.NEGATIVE_INFINITY;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        if (this.y == that.y) return +0.0;
        else                  return 1.0 * (this.y - that.y) / (this.x - that.x);
    }

    private boolean isEqualTo(Point that) {
        return (this.y == that.y && this.x == that.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (that == null) throw new NullPointerException("Argument to compareTo cannot be null");
        if (this.x == that.x && this.y == that.y) return 0;
        if ((this.y == that.y && this.x < that.x) || this.y < that.y)
            return -1;
        else return +1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            public int compare(Point q, Point r) {
                double slope1 = q.slopeTo(Point.this);
                double slope2 = r.slopeTo(Point.this);
                return (slope1 == slope2 ? 0 : (slope1 > slope2 ? +1 : -1));
            }
        };
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p = new Point(156, 484);
        Point q = new Point(396, 133);
        System.out.printf("Symmetric: %f, %f \n", p.slopeTo(q), q.slopeTo(p));

        p = new Point(3517, 28768);
        q = new Point(22154, 28156);
        System.out.printf("Symmetric: %f, %f \n", p.slopeTo(q), q.slopeTo(p));

        p = new Point(435, 318);
        q = new Point (469, 393);
        System.out.printf("Student, reference: %f, %f \n", p.slopeTo(q), 2.205);


        p  = new Point (111, 327);
        q  = new Point (333, 301);
        Point r = new Point(390, 178);
        System.out.printf("Student, reference: %d, %d \n", p.slopeOrder().compare(q, r), 1);
        System.out.printf("Student, reference: %f, %f \n", p.slopeTo(q), -0.117);


        p = new Point(9, 6);
        q = new Point(7, 7);
        r = new Point(6, 9);
        System.out.printf("Student, reference: %d, %d \n", p.slopeOrder().compare(q, r), 1);

    }
}
