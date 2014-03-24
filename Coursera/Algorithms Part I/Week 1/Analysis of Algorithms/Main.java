public class Main {
	// driver class
	public static void main(String[] args) {
		ThreeSum threeSum = new ThreeSum();
		ThreeSumFast threeSumFast = new ThreeSumFast();
		int cnt;
		Stopwatch timer;
		In in;
		int[] a;

		in = new In("1Kints.txt");
		StdOut.println("1K integers:");
        a = in.readAllInts();

        timer = new Stopwatch();
        cnt = threeSum.count(a);
        StdOut.println("ThreeSum:\t elapsed time = " + timer.elapsedTime() + ", " + cnt);

        timer = new Stopwatch();
        cnt = threeSumFast.count(a);
        StdOut.println("ThreeSumFast:\t elapsed time = " + timer.elapsedTime() + ", " + cnt);

        in = new In("2Kints.txt");
		StdOut.println("2K integers:");
        a = in.readAllInts();

        timer = new Stopwatch();
        cnt = threeSum.count(a);
        StdOut.println("ThreeSum:\t elapsed time = " + timer.elapsedTime() + ", " + cnt);

        timer = new Stopwatch();
        cnt = threeSumFast.count(a);
        StdOut.println("ThreeSumFast:\t elapsed time = " + timer.elapsedTime() + ", " + cnt);

        in = new In("4Kints.txt");
		StdOut.println("4K integers:");
        a = in.readAllInts();

        timer = new Stopwatch();
        cnt = threeSum.count(a);
        StdOut.println("ThreeSum:\t elapsed time = " + timer.elapsedTime() + ", " + cnt);

        timer = new Stopwatch();
        cnt = threeSumFast.count(a);
        StdOut.println("ThreeSumFast:\t elapsed time = " + timer.elapsedTime() + ", " + cnt);

        in = new In("8Kints.txt");
		StdOut.println("8K integers:");
        a = in.readAllInts();

        timer = new Stopwatch();
        cnt = threeSum.count(a);
        StdOut.println("ThreeSum:\t elapsed time = " + timer.elapsedTime() + ", " + cnt);

        timer = new Stopwatch();
        cnt = threeSumFast.count(a);
        StdOut.println("ThreeSumFast:\t elapsed time = " + timer.elapsedTime() + ", " + cnt);
	}
}