/*
Find next higher number with same digits. 

Example 1 : if num = 25468, o/p = 25486 
Example 2 : if num = 21765, o/p = 25167 
Example 3 : If num = 54321, o/p = 54321 (cause it's not possible to gen a higher num than tiz with given digits ).
*/

class Solution {
	// get number of specific digit
	private static int getDigit(int num, int digit) {
		for(int i = 0; i < digit; i++) {
			num /= 10;
		}
		return num % 10;
	}
	// get count of number
	private static int getCount(int num) {
		int count = 0;
		while(num > 0) {
			num /= 10;
			count++;
		}
		return count == 0 ? 1 : count;
	}

	private static int swap(int num, int n1, int n2, int b1, int b2) {
		// clean
		num -= n1 * b1;
		num -= n2 * b2;
		// add
		num += n2 * b1;
		num += n1 * b2;
		return num;
	}

	public static int nextHigher(int num) {
		int count = getCount(num), bit = 1, higher = num, digit = 0;
		for(int i = 0; i < count; i++) {
			int pivot = getDigit(num, i), obit = bit * 10;
			for(int j = i + 1; j < count; j++) {
				int other = getDigit(num, j);
				if(pivot > other) {	// we can make higher number
					higher = swap(higher, pivot, other, bit, obit);
					// mark
					digit = j;
					break;
				}
				obit *= 10;
			}
			if(higher > num) break;
			bit *= 10;
		}
		// need sort the left
		bit = 1;
		for(int i = 0; i < digit; i++) {
			int pivot = getDigit(higher, i), obit = bit * 10;
			for(int j = i + 1; j < digit; j++) {
				int other = getDigit(higher, j);
				if(pivot < other) {
					higher = swap(higher, pivot, other, bit, obit);
					pivot = other;
				}
				obit *= 10;
			}
			bit *= 10;
		}
		return higher;
	}

	public static void main(String[] args) {
		System.out.println(nextHigher(21765));
	}
}