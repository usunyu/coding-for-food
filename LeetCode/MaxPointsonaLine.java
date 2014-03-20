/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

import java.util.ArrayList;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

class Solution {
    final double SMALL_DECIMAL = 0.000001;

    public int maxPoints(Point[] points) {
        if(points.length <= 2) return points.length;
        int max = 0;
        for(int i = 0; i < points.length - 1; i++) {
            for(int j = i + 1; j < points.length; j++) {
                int sum = 0;
                if(points[j].x - points[i].x != 0) {
                    double k = (double)(points[j].y - points[i].y) / (points[j].x - points[i].x);
                    double b = points[j].y - points[j].x * k;
                    // check if point in the line
                    for(int p = 0; p < points.length; p++) {
                        if(p == i || p == j) sum++;
                        else if(Math.abs(points[p].y - (k * points[p].x + b)) < SMALL_DECIMAL) sum++;
                    }
                }
                else {
                    for(int p = 0; p < points.length; p++) {
                        if(points[p].x == points[j].x) sum++;
                    }
                }
                if(sum > max) {
                    max = sum;
                }
                if(max == points.length) {
                    return max;
                }
            }
        }
        return max;
    }
    /*
        Second Round
    */
    public int maxPoints2(Point[] points) {
        if(points.length <= 2) return points.length;
        int max = 0;
        // calculate every possible K
        for(int i = 0; i < points.length - 1; i++) {
            for(int j = i + 1; j < points.length; j++) {
                int count = 0;
                int deltaX = points[j].x - points[i].x;
                if(deltaX != 0) {   // K is normal
                    int deltaY = points[j].y - points[i].y;
                    double K = (double) deltaY / deltaX;
                    double b = (double)points[j].y - K * points[j].x;
                    // check every point
                    for(int x = 0; x < points.length; x++) {
                        if(x == i || x == j) count++;
                        else if(Math.abs(points[x].y - K * points[x].x - b) < 0.000001) count++;
                    }
                }
                else {  // K is infinity
                    for(int x = 0; x < points.length; x++) {
                        if(points[x].x == points[i].x) count++;
                    }
                }
                max = Math.max(count, max);
                if(max == points.length) return max;
            }
        }
        return max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println((solution.maxPoints2(buildExamplePoints())));
    }

    public static Point[] buildExamplePoints() {
        ArrayList<Point> list = new ArrayList<Point>();
        list.add(new Point(3,1));
        list.add(new Point(12,3));
        list.add(new Point(3,1));
        list.add(new Point(-6,-1));
        Point[] points = new Point[list.size()];
        for(int i = 0; i < points.length; i++) {
            points[i] = list.get(i);
        }
        return points;
    }
}
