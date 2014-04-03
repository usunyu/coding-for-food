/*
http://www.geeksforgeeks.org/recursive-functions/
*/

import java.io.*;

class TowersApp {
	static int nDisks = 3;

	// Assuming n-th disk is bottom disk (count down)
	public static void doTowers(int topN, char from, char inter, char to) {
		if(topN == 1) {	// Base case (termination condition)
			System.out.println("Disk 1 from " + from + " to "+ to);
		}
		else {
			// Move first n-1 disks from source pole
   			// to auxiliary pole using destination as
   			// temporary pole
			doTowers(topN - 1, from, to, inter);
			// Move the remaining disk from source
   			// pole to destination pole
			System.out.println("Disk " + topN + " from " + from + " to "+ to);
			// Move the n-1 disks from auxiliary (now source)
   			// pole to destination pole using source pole as
   			// temporary (auxiliary) pole
			doTowers(topN - 1, inter, from, to);
		}
	}

	public static void main(String[] args) {
		doTowers(nDisks, 'A', 'B', 'C');
	}
}