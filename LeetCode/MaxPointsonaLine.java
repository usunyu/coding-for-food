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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Point> list = new ArrayList<Point>();
        list.add(new Point(3,1));
        list.add(new Point(12,3));
        list.add(new Point(3,1));
        list.add(new Point(-6,-1));
        Point[] points = new Point[list.size()];
        for(int i = 0; i < points.length; i++) {
            points[i] = list.get(i);
        }
        System.out.println((solution.maxPoints(points)));
    }
}
