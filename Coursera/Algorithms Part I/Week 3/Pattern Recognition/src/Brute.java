/*
Brute force. Write a program Brute.java that examines 4 points at a time and checks whether they all lie on the 
same line segment, printing out any such line segments to standard output and drawing them using standard drawing. 
To check whether the 4 points p, q, r, and s are collinear, check whether the slopes between p and q, 
between p and r, and between p and s are all equal.

The order of growth of the running time of your program should be N4 in the worst case and it should use space 
proportional to N.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Brute {
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
	
	private static void collinear() {
		collinears = new ArrayList<ArrayList<Point>>();
		for(int a = 0; a < points.size() - 3; a++) {
			Point p1 = points.get(a);
			for(int b = a + 1; b < points.size() - 2; b++) {
				Point p2 = points.get(b);
				double slope1 = p1.slopeTo(p2);
				if(slope1 == Double.NEGATIVE_INFINITY)
					continue;
				for(int c = b + 1; c < points.size() - 1; c++) {
					Point p3 = points.get(c);
					double slope2 = p1.slopeTo(p3);
					if(slope2 == Double.NEGATIVE_INFINITY)
						continue;
					if(Math.abs(slope1 - slope2) >= Precision)	// not equal
						continue;
					for(int d = c + 1; d < points.size(); d++) {
						Point p4 = points.get(d);
						double slope3 = p1.slopeTo(p4);
						if(slope3 == Double.NEGATIVE_INFINITY)
							continue;
						if(Math.abs(slope1 - slope3) < Precision ||
								slope1 == Double.POSITIVE_INFINITY &&
								slope2 == Double.POSITIVE_INFINITY &&
								slope3 == Double.POSITIVE_INFINITY) {
							ArrayList<Point> collinear = new ArrayList<Point>();
							collinear.add(p1);
							collinear.add(p2);
							collinear.add(p3);
							collinear.add(p4);
							collinears.add(collinear);
						}
					}
				}
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
