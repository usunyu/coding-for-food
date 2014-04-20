/*
You are given two 32-bit numbers,N and M, and two bit positions, i and j. 
Write a method to insert M into N such that M starts at bit j and ends at bit i. 
You can assume that the bits j through i have enough space to fit all of M. That is, if M = 10011, you can assume 
that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because
M could not fully fit between bit 3 and bit 2.

EXAMPLE:
Input:N=10000000000, M=10011, i=2, j=6 Output: N = 10001001100
*/

import java.util.*;

class BitObject {
	int decimal;

	public BitObject(int decimal) {
		this.decimal = decimal;
	}

	public void display() {
		System.out.print(decimal + " ");
	}

	public void displayBinary() {
		Stack<Integer> stack = new Stack<Integer>();

		int dec = decimal;
		while(dec != 0) {
			int bit = dec % 2;
			stack.push(bit);
			dec /= 2;
		}

		while(!stack.isEmpty())
			System.out.print(stack.pop());
		System.out.print(" ");
	}

	public void clear(int start, int end) {
		if(start < end)
			return;
		int length = start - end + 1;
		int mask = ~(((1 << length) - 1) << end);
		decimal &= mask;
	}

	public void insert(int m, int start, int end) {
		clear(start, end);
		int mask = m << end;
		decimal |= mask;
	}
	/*
		Second Round
	*/
	public void clear2(int start, int end) {
		if(start < end) return;
		int mask = ~(((1 << (start + 1)) - 1) & (~((1 << (end + 1)) - 1)));
		decimal &= mask;
	}

	public void insert2(int m, int start, int end) {
		clear2(start, end);
		int mask = m << end;
		decimal |= mask;
	}
}

class Solution {
	public static void main(String[] args) {
		BitObject N = new BitObject(1024);
		N.display();
		System.out.print("\t:\t");
		N.displayBinary();
		System.out.println();

		BitObject M = new BitObject(19);
		M.display();
		System.out.print("\t:\t");
		M.displayBinary();
		System.out.println();

		N.insert2(M.decimal, 6, 2);
		System.out.println("After insert:");
		N.display();
		System.out.print("\t:\t");
		N.displayBinary();
		System.out.println();
	}
}
