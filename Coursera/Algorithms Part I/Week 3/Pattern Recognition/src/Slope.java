
public class Slope implements Comparable<Slope> {
	private double slope;
	private Point fromPoint;
	private Point toPoint;
	
	public Slope(Point f, Point t) {
		this.fromPoint = f;
		this.toPoint = t;
		this.slope = fromPoint.slopeTo(toPoint);
	}
	
	public double getSlope() {
		return slope;
	}
	
	public Point getFromPoint() {
		return fromPoint;
	}
	
	public Point getToPoint() {
		return toPoint;
	}
	
	@Override
	public int compareTo(Slope that) {
		if(this.slope < that.slope) return -1;
		else if(this.slope > that.slope) return 1;
		else return 0;
	}

	@Override
	public String toString() {
		return "[" + slope + "]";
	}
}
