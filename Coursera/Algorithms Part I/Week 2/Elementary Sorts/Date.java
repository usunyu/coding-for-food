public class Date implements Comparable<Date>
{
	private final int month, day, year;

	public Date(int m, int d, int y) {
		month = m; 
		day = d;
		year = y;
	}

	public int compareTo(Date that) {
		if (this.year < that.year ) return -1;
		if (this.year > that.year ) return +1;
		if (this.month < that.month) return -1;
		if (this.month > that.month) return +1;
		if (this.day < that.day ) return -1;
		if (this.day > that.day ) return +1;
		return 0;
	}
}