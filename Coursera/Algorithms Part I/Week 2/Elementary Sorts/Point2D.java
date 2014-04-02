public class Point2D {
	private final double x;
	private final double y;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// ...

	public static int ccw(Point2D a, Point2D b, Point2D c) {
		double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
		if 		(area2 < 0) return -1;	// clockwise
		else if (area2 > 0) return 1;	// counter-clockwise
		else				return 0;	// collinear
	}
}