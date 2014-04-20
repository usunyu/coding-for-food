/*
Given a real number between 0 and 7 (e.g., 0.72) that is passed in as a double, 
print the binary representation. If the number cannot be represented accurately in binary 
with at most 32 characters, print "ERROR."
*/

import java.util.*;

class BitObject {
	double decimal;

	public BitObject(double decimal) {
		this.decimal = decimal;
	}

	public void display() {
		System.out.print(decimal + " ");
	}

	public void displayBinary() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		double dec = decimal;
		int num = 0;
		while(dec != 1) {
			dec *= 2;
			int bit = (int)dec;
			list.add(bit);
			num++;
			if(dec > 1)
				dec -= 1;
			if(num > 32)
				break;
		}
		if(num <= 32) {
			ListIterator<Integer> iterator = list.listIterator();
			System.out.print("0.");
			while(iterator.hasNext())
				System.out.print(iterator.next());
			System.out.print(" ");
		}
		else
			System.out.print("ERROR");
	}
}

class Solution {
	public static void main(String[] args) {
		BitObject bo = new BitObject(0.8125);
		bo.display();
		System.out.print("\t:\t");
		bo.displayBinary();
		System.out.println();
	}
}

