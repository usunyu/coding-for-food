/*
A permutation, also called an “arrangement number” or “order,” is a rearrangement of the elements of an ordered list S 
into a one-to-one correspondence with S itself. A string of length n has n! permutation.
Source: Mathword(http://mathworld.wolfram.com/Permutation.html)

Below are the permutations of string ABC.
ABC, ACB, BAC, BCA, CAB, CBA
*/

import java.util.ArrayList;

class Main {
	/* Function to swap values at two pointers */
	private static void swap(char[] charArray, int i, int j) {
		char tmp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = tmp;
	}

	/* Function to print permutations of string
	   This function takes three parameters:
	   1. String
	   2. Starting index of the string
	   3. Ending index of the string. */
	private static void permute(char[] charArray, int index) {
		if(index == charArray.length - 1) {
			System.out.println(charArray);
		}
		else {
			for(int i = index; i < charArray.length; i++) {
				swap(charArray, index, i);
				permute(charArray, index + 1);
				swap(charArray, index, i);	// backtrack
			}
		}
	}

	public static void permute(char[] charArray) {
		permute(charArray, 0);
	}

	// solution from CTCI
	public static void permute2(char[] charArray) {
		ArrayList<StringBuilder> permutations = new ArrayList<StringBuilder>();
		// initial
		permutations.add(new StringBuilder());
		for(int i = 0; i < charArray.length; i++) {
			ArrayList<StringBuilder> tmp = new ArrayList<StringBuilder>();
			for(int j = 0; j < permutations.size(); j++) {
				StringBuilder sb = permutations.get(j);
				for(int k = 0; k <= sb.length(); k++) {
					StringBuilder st = new StringBuilder(sb);
					st.insert(k, charArray[i]);
					tmp.add(st);
				}
			}
			permutations = tmp;
		}
		for(StringBuilder sb : permutations) {
			System.out.println(sb.toString());
		}
	}

	/* Driver program to test above functions */
	public static void main(String[] args) {
		String str = "ABC";
		char[] charArray = str.toCharArray();
		permute2(charArray);
	}
}