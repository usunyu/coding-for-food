/*************************************************************************
 * Name: Yu Sun
 * Email: sun812@usc.edu
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point a, Point c) {
            double res = slopeTo(a) - slopeTo(c);
            if      (res < 0.0) return -1;
            else if (res > 0.0) return  1;
            else                return  0;
        }
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	if(that.x == x && that.y == y)
    		return Double.NEGATIVE_INFINITY;
    	else if(that.x == x) 
    		return Double.POSITIVE_INFINITY;
    	else
    		return (double)(that.y - y) / (that.x - x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if(y < that.y) 			return -1;
    	else if(y > that.y) 	return 1;
    	else {
    		if(x < that.x) 		return -1;
    		else if(x > that.x) return 1;
    		else return 		0;
    	}
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	Point p, q, r;
        p = new Point(87, 479);
        q = new Point(87, 479);
        assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;
        p = new Point(25681, 22210);
        q = new Point(25681, 22210);
        assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;
        p = new Point(3, 4);
        q = new Point(3, 4);
        assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;
        p = new Point(8, 5);
        q = new Point(9, 4);
        r = new Point(8, 5);
        assert p.SLOPE_ORDER.compare(q, r) == 1;
        assert p.slopeTo(q) == -1.0;
        assert p.slopeTo(r) == Double.NEGATIVE_INFINITY;
    }
}