/*
An array A contains all the integers from 0 through n, except for one number which is missing. 
In this problem, we cannot access an entire integer in A with a single operation. 
The elements of A are represented in binary, and the only operation we can use to access them is
"fetch the jth bit of A[i]," which takes constant time.
Write code to find the missing integer. Canyou do it in O(n) time?
*/

import java.util.*;

class BitObject {
	int decimal;

	public BitObject(int decimal) {
		this.decimal = decimal;
	}

	public void display() {
		displayDecimal();
		System.out.print("\t:\t");
		displayBinary();
		System.out.println();
	}

	public void displayDecimal() {
		System.out.print(decimal + " ");
	}

	public void displayBinary() {
		Stack<Integer> stack = new Stack<Integer>();

		int dec = decimal;
		if(dec == 0) {
			System.out.print(dec + " ");
			return;
		}

		while(dec != 0) {
			int bit = dec % 2;
			stack.push(bit);
			dec /= 2;
		}

		while(!stack.isEmpty())
			System.out.print(stack.pop());
		System.out.print(" ");
	}

	public int fetch(int index) {
		int mask = 1 << index;
		return (((decimal & mask) == 0) ? 0 : 1);
	}
}

class Solution {
	public static int findMiss(BitObject[] bArray) {
		int miss;
		for(miss = 0; miss < bArray.length; miss++) {
			int check = bArray[miss].fetch(0);
			if(check != miss % 2)
				break;
		}
		return miss;
	}

	public static int findMiss2(BitObject[] bArray) {
		ArrayList<Integer> bitArray = new ArrayList<Integer>(bArray.length);
		for(int i = 0; i < bArray.length; i++)
			bitArray.add(bArray[i].decimal);
		return findMiss2Rec(bitArray, 0);
	}

	public static int fetch(int num, int index) {
		int mask = 1 << index;
		return (num & mask) == 0 ? 0 : 1;
	}

	public static int findMiss2Rec(ArrayList<Integer> input, int column) {
		if(column >= 16)
			return 0;
		ArrayList<Integer> oneBits = new ArrayList<Integer>(input.size() / 2);
		ArrayList<Integer> zeroBits = new ArrayList<Integer>(input.size() / 2);

		for(int i : input) {
			if(fetch(i, column) == 1)
				oneBits.add(i);
			else
				zeroBits.add(i);
		}

		if(zeroBits.size() <= oneBits.size()) {
			int v = findMiss2Rec(zeroBits, column + 1);
			return (v << 1) | 0;
		}
		else {
			int v = findMiss2Rec(oneBits, column + 1);
			return (v << 1) | 1;
		}
	}

	public static void main(String[] args) {
		int SIZE = 20;

		BitObject[] bArray = new BitObject[SIZE];
		for(int i = 0; i < SIZE; i++) {
			if(i >= 10)
				bArray[i] = new BitObject(i + 1);
			else
				bArray[i] = new BitObject(i);
		}

		for(int i = 0; i < SIZE; i++)
			bArray[i].display();

		int miss = findMiss2(bArray);
		System.out.println("Find miss number: " + miss);
	}
}



