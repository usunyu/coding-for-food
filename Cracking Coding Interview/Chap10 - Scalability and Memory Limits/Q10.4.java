/*
You have an array with all the numbers from 1 to N, where N is at most 32,000. 
The array may have duplicate entries and you do not know what N is. With only 4 kilobytes of memory available, 
how would you print all duplicate elements in the array?
*/

import java.util.*;

class Solution {
	static ArrayList<Integer> intList = new ArrayList<Integer>();

	public static boolean check(byte[] byteArray, int value) {
		int index = value / 8;
		int offset = value % 8;
		int bit = byteArray[index] >> offset;
		if(bit == 1)
			return true;
		else
			return false;
	}

	public static void set(byte[] byteArray, int value) {
		int index = value / 8;
		int offset = value % 8;
		byteArray[index] |= (1 << offset);
	}

	public static void main(String[] args) {
		int N = 32000;
		byte[] byteArray = new byte[N / 8];

		// set 0
		for(int i = 0; i < byteArray.length; i++)
			byteArray[i] = 0;

		for(int i = 0; i < intList.size(); i++) {
			int value = intList.get(i);
			if(check(byteArray, value)) {	// dup
				System.out.println(value);
			}
			else {	// set
				set(byteArray, value);
			}
		}
	}
}