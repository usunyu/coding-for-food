/*
Given an input file with four billion non-negative integers, provide an algorithm to generate an integer 
which is not contained in the file. Assume you have 1 GB of memory availablefor this task.

FOLLOW UP
What if you have only 10 MB of memory? Assume that all the values are distinct and we now have no more 
than one billion non-negative integers.
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Solution {
	public static long numberOfInts = ((long) Integer.MAX_VALUE) + 1;
	public static byte[] bitfield = new byte [(int) (numberOfInts / 8)];

	public static void findOpenNumber() throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader("text.txt"));
		while (in.hasNextInt()) {
			int n = in.nextInt ();
			/* Finds the corresponding number in the bitfield by using
			 * the OR operator to set the nth bit of a byte 
			 * (e.g., 10 would correspond to the 2nd bit of index 2 in
			 * the byte array). */
			bitfield [n / 8] |= 1 << (n % 8);
		}

		for (int i = 0; i < bitfield.length; i++) {
			for (int j = 0; j < 8; j++) {
				/* Retrieves the individual bits of each byte. When 0 bit
				 * is found, finds the corresponding value. */
				if ((bitfield[i] & (1 << j)) == 0) {
					System.out.println (i * 8 + j);
					return;
				}
			}
		}		
	}

	public static void main(String[] args)  throws IOException {
		findOpenNumber();
	}
}