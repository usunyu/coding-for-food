/*
A faster, sorting-based solution. Remarkably, it is possible to solve the problem much faster than the brute-force 
solution described above. 
Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.

Think of p as the origin.
For each other point q, determine the slope it makes with p.
Sort the points according to the slopes they makes with p.
Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. 
If so, these points, together with p, are collinear.
Applying this method for each of the N points in turn yields an efficient algorithm to the problem. 
The algorithm solves the problem because points that have equal slopes with respect to p are collinear, 
and sorting brings such points together. The algorithm is fast because the bottleneck operation is sorting.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Fast {
	private static ArrayList<Point> points;
	private static ArrayList<ArrayList<Point>> collinears;
	private static double Precision = 0.0001;
	
	private static void input(String filename) {
		points = new ArrayList<Point>();
		File file = new File(filename);
		Scanner sc;
		int num;
		Point point;
		try {
			sc = new Scanner(file);
			num = sc.nextInt();	// line count
			for(int i = 0; i < num; i++) {
				point = new Point(sc.nextInt(), sc.nextInt());
				points.add(point);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void addCollinear(Point origin, Slope slope, Slope prev, int i, int j, int count, ArrayList<Slope> slopes) {
		// check if contained
		boolean contained = false;
		for(int k = 0; k < i; k++) {
			Slope check = new Slope(points.get(k), origin);
			if(Math.abs(slope.getSlope() - check.getSlope()) < Precision) {
				contained = true;
				break;
			}
		}
		if(contained)
			return;
		int n;
		if(Math.abs(slope.getSlope() - prev.getSlope()) < Precision)
			n = j + 1;
		else
			n = j;
		ArrayList<Point> collinear = new ArrayList<Point>();
		collinear.add(origin);
		for(int k = j - count; k < n; k++) {
			collinear.add(slopes.get(k).getToPoint());
		}
		collinears.add(collinear);
	}
	
	private static void addCollinear(Point origin, int i, int j, ArrayList<Slope> slopes) {
		// check if contained
		boolean contained = false;
		for(int k = 0; k < i; k++) {
			Slope check = new Slope(points.get(k), origin);
			if(check.getSlope() == Double.POSITIVE_INFINITY) {
				contained = true;
				break;
			}
		}
		if(contained)
			return;
		ArrayList<Point> collinear = new ArrayList<Point>();
		collinear.add(origin);
		for(int k = j; k < slopes.size(); k++)
			collinear.add(slopes.get(k).getToPoint());
		collinears.add(collinear);
	}
	
	private static void collinear() {
		collinears = new ArrayList<ArrayList<Point>>();
		for(int i = 0; i < points.size() - 3; i++) {
			Point origin = points.get(i);
			ArrayList<Slope> slopes = new ArrayList<Slope>();
			for(int j = i + 1; j < points.size(); j++) {
				Point to = points.get(j);
				Slope slope = new Slope(origin, to);
				if(slope.getSlope() == Double.NEGATIVE_INFINITY)
					continue;
				slopes.add(slope);
			}
			// sort by slope
			Collections.sort(slopes);
			int count = 1;
			Slope prev = null;
			for(int j = 0; j < slopes.size(); j++) {
				Slope slope = slopes.get(j);
				// if infinity
				if(slope.getSlope() == Double.POSITIVE_INFINITY) {
					// process prevs
					if(count >= 3 && prev != null)
						addCollinear(origin, slope, prev, i, j, count, slopes);
					// precess infinity
					if(slopes.size() - j >= 3)
						addCollinear(origin, i, j, slopes);
					break;
				}
				if(prev != null) {
					if(Math.abs(slope.getSlope() - prev.getSlope()) > Precision || j == slopes.size() - 1) {
						if(count >= 3)
							addCollinear(origin, slope, prev, i, j, count, slopes);
						count = 1;
					}
					else {
						count++;
					}
				}
				prev = slope;
			}
		}
	}
	
	private static void drawPoints() {
		for(Point p : points)
			p.draw();
	}
	
	private static void drawCollinears() {
		for(ArrayList<Point> collinear : collinears) {
			Point prev = null;
			for(Point point : collinear) {
				if(prev != null)
					prev.drawTo(point);
				prev = point;
			}
		}
	}
	
	private static void printCollinears() {
		for(ArrayList<Point> collinear : collinears) {
			for(int i = 0; i < collinear.size(); i++) {
				Point point = collinear.get(i);
				System.out.print(point);
				if(i < collinear.size() - 1)
					System.out.print(" -> ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
		
		input(args[0]);
		Collections.sort(points);
		drawPoints();
		collinear();
		drawCollinears();
		printCollinears();
		
		StdDraw.show(0);
	}
}
